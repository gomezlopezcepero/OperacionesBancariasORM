/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import banco.CuentasBancarias;
import banco.HibernateUtil;
import static com.sun.org.glassfish.external.amx.AMXUtil.prop;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Paco
 */
public class OperacionesBancoORM {

    ArrayList<Operaciones> opes =null;
       Session session=null;
         org.hibernate.Transaction tx = null;
       
        public OperacionesBancoORM(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
         this.session = sessionFactory.openSession();
        org.hibernate.Transaction tx = session.beginTransaction();
    }
    
    
public  void insertarCuenta(String numCuenta,String dni,String usuario,String numSecreto,String nombre, String primerApellido,String segundoApellido){
//añade un objeto nuevo a la base de datos (persistencia)
  
               Transaction tx=null; 
        Session session = HibernateUtil.getSessionFactory().openSession();
        tx=session.beginTransaction(); //Crea una transacción
       
        
        CuentasBancarias c = new CuentasBancarias();
        
        c.setDni(dni);
        c.setNumeroCuenta(numCuenta);
        c.setPropietario(usuario);
        c.setSaldo(0);
        
        session.save(c);
       Propietarios op = new Propietarios();
       
       
    //op.setIdPropietario(15);
    op.setDni(dni);
    op.setNombre(nombre);
    op.setNumeroSecreto(numSecreto);
    op.setPrimerApellido(primerApellido);
    op.setSegundoApellido(segundoApellido);
    op.setUsuario(usuario);

    
  session.save(op);
        //Guarda el objeto creado en la BBDD.
        tx.commit(); //Materializa la transacción
        session.close();
        
        
    }

public  Operaciones verOperacion(int id)
    {//recupera un objeto cuyo id se pasa como parámentro
        Transaction tx=null; 
        Session session = HibernateUtil.getSessionFactory().openSession();
        tx=session.beginTransaction(); //Crea una transacción
        Operaciones op ;
        op=(Operaciones)session.get(Operaciones.class,id);
        
       
        tx.commit(); //MAterializa la transacción
        session.close();
    
    return op;
    }




public Iterator verOperaciones(){
    
    Session session = HibernateUtil.getSessionFactory().openSession();
        
    List ops=  session.createCriteria(Operaciones.class).list();
     
    Iterator opesiterator= ops.iterator();
     
    return opesiterator;
}


public Iterator verOperaciones(String numCuenta){
    
    Session session = HibernateUtil.getSessionFactory().openSession();
        
    List ops=  session.createCriteria(Operaciones.class).add(Restrictions.like("numCuenta", numCuenta)).list();
     
    Iterator opesiterator= ops.iterator();
     
    return opesiterator;

}

public String sacarNumCuenta(String dni){
    
    Session session = HibernateUtil.getSessionFactory().openSession();
        
    CuentasBancarias cuent=  (CuentasBancarias) session.createCriteria(CuentasBancarias.class).add(Restrictions.like("dni", dni)).uniqueResult();
     
    return cuent.getNumeroCuenta();

}


public Iterator verNumerosRojos(){
    
   Session session = HibernateUtil.getSessionFactory().openSession();
        Query q= session.createQuery("SELECT a from CuentasBancarias a where saldo < 0 order by saldo");
        List ops = q.list();

    Iterator opesiterator= ops.iterator();
     
    return opesiterator;
}


public Iterator operacionesGeneradoNumerosRojos(){
    
   Session session = HibernateUtil.getSessionFactory().openSession();
        Query q= session.createQuery("from Operaciones as opes where opes.fechaHora in (select histo.fechaHora from Historial as histo where histo.tipoEvento = 'R')");
        List ops = q.list();

    Iterator opesiterator= ops.iterator();
     
    return opesiterator;
}


public Historial verFechaLogin(String fecha){
    
   Session session = HibernateUtil.getSessionFactory().openSession();
        
       Historial histo=  (Historial) session.createCriteria(Historial.class).add(Restrictions.like("fechaHora", fecha)).uniqueResult();

     return histo;
}

public int verMiRanking(String dni){
    
    int cont=0;
    
   Session session = HibernateUtil.getSessionFactory().openSession();
        
       List cuents=   session.createCriteria(CuentasBancarias.class).addOrder(Order.desc("saldo")).list();

         Iterator cuentsiterator= cuents.iterator();
       while (cuentsiterator.hasNext())
                {
                    cont++;
                     CuentasBancarias a2= (CuentasBancarias)cuentsiterator.next();
                
                     if(a2.getDni().equals(dni)){
                        break; 
                     }
                }
       
     return cont;
}


 public  boolean verificarExistencia(String dni, String numSecreto, String fecha)
    {//recupera un objeto cuyo id se pasa como parámentro
        
        boolean bulean = true;
        
        Transaction tx=null; 
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        tx=session.beginTransaction(); //Crea una transacción
      
        //comprueba si el dni y la clave coinciden
        
        Propietarios prop= null;
       prop=  (Propietarios) session.createCriteria(Propietarios.class).add( Restrictions.like("dni", dni))
               .add( Restrictions.like("numeroSecreto", numSecreto))
               .uniqueResult();
       
       if(prop==null){
         bulean = false;
       }
       else{
           // añade el login al historial
           
           CuentasBancarias cuent= null;
           cuent=  (CuentasBancarias) session.createCriteria(CuentasBancarias.class).add( Restrictions.like("dni", dni))
               .uniqueResult();
        
           Historial h = new Historial();
       
       h.setFechaHora(fecha);
       h.setNumeroCuenta(cuent.getNumeroCuenta());
       h.setTipoEvento("L");
       
       session.save(h);
           
           
       }
       // System.out.println(prop.getNombre());
        tx.commit(); //MAterializa la transacci-ón
        session.close();
        
        return bulean;
    }

 
      public void ActualizarDatosPropietario(String dni, String nombre, String primerApellido, String segundoApellido)
    {//Modifica un objeto cuyo id se pasa como parámetro
        Transaction tx=null; 
        Session session = HibernateUtil.getSessionFactory().openSession();
        tx=session.beginTransaction(); //Crea una transacción
 
        
        Query query = session.createQuery("update Propietarios set nombre = '"+nombre+"',  primerApellido = '"+primerApellido+"',  segundoApellido = '"+segundoApellido+"' where dni = '"+dni+"'");
query.executeUpdate();

               
        tx.commit(); //MAterializa la transacción
        session.close();
        
    }
 
 


     public void ActualizarNumeroSecreto(String dni, String numSecreto)
    {//Modifica un objeto cuyo id se pasa como parámetro
        Transaction tx=null; 
        Session session = HibernateUtil.getSessionFactory().openSession();
        tx=session.beginTransaction(); //Crea una transacción
 
        
        Query query = session.createQuery("update Propietarios set numeroSecreto = '"+numSecreto+"' where dni = '"+dni+"'");
        query.executeUpdate();

               
        tx.commit(); //MAterializa la transacción
        session.close();
        
    }
 
 
     public void borrarCuenta(String dni)
    {//Borrar un objeto cuyo id se pasa como parámentro
        Transaction tx=null; 
        Session session = HibernateUtil.getSessionFactory().openSession();
        tx=session.beginTransaction(); //Crea una transacción
      
        Query query = session.createQuery("DELETE FROM Propietarios WHERE dni = '"+dni+"'");
        query.executeUpdate();
        
        Query query2 = session.createQuery("DELETE FROM CuentasBancarias WHERE dni = '"+dni+"'");
        query2.executeUpdate();
       

        
        System.out.println ("Objeto borrado");
        tx.commit(); //MAterializa la transacción
        session.close();
    }
     
     
     public void hacerOperacion(String dni, int cantidad, String operacion, String fecha){
         
         boolean bulean = false;
         int saldo=0;
        
        Transaction tx=null; 
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        tx=session.beginTransaction(); //Crea una transacción
      
        //saca los valore del objeto seleccionado
        
        CuentasBancarias cuent= null;
     
       cuent=  (CuentasBancarias) session.createCriteria(CuentasBancarias.class)
               .add( Restrictions.like("dni", dni))
               .uniqueResult();
       
       if(operacion.equals("e")){
                   saldo = cuent.getSaldo() - cantidad;
               }
               else{
                   saldo = cuent.getSaldo() + cantidad;                     
               }
       
       //si el saldo pasa a ser negativo lo marca como true
       
       if(cuent.getSaldo()>0&&saldo<0){
           bulean =true;
       }
       
       //inserta la operacion en el historial
       
       Historial h = new Historial();
       
       h.setFechaHora(fecha);
       h.setNumeroCuenta(cuent.getNumeroCuenta());
       h.setTipoEvento(operacion.toUpperCase());
       
       session.save(h);
       
       //inserta la nueva operación
       
       Operaciones o = new Operaciones();
       
       o.setFechaHora(fecha);
       o.setNumCuenta(cuent.getNumeroCuenta());
       o.setTipoOperacion(operacion);
       o.setCantidad(cantidad);
       
       session.save(o);
               
       //actualiza el saldo del propietario 
       
        Query query = session.createQuery("UPDATE CuentasBancarias SET saldo='"+saldo+"' WHERE dni='"+dni+"'");
        query.executeUpdate();
        
        if(bulean){
            
               Historial h2 = new Historial();
       
               h2.setFechaHora(fecha);
               h2.setNumeroCuenta(cuent.getNumeroCuenta());
               h2.setTipoEvento("R");
       
             session.save(h2);
            
        }
        
         tx.commit(); //MAterializa la transacci-ón
        session.close();
        
        /* OTRO TIPO DE INSERTS
       Query query = session.createSQLQuery("INSERT INTO Operaciones (Fecha_hora, numCuenta, tipoOperacion, cantidad) VALUES (:valor1,:valor2,:valor3,:valor4)");   
       
        query.setParameter("valor1", fecha);
        query.setParameter("valor2", cuent.getNumeroCuenta());
        query.setParameter("valor3", operacion);
        query.setParameter("valor4", cantidad);

        query.executeUpdate();
       */
        
        
     }
     
 
    
}
