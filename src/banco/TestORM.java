/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;






/**
 *
 * @author Paco
 */
public class TestORM {


    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    


    OperacionesBancoORM conn = new OperacionesBancoORM();    
        
    int elec = 0, elecUsuario=0;
        
    int cont=0;
     String numCuenta="",dni="",usuario="",numSecreto="",nombre="",primerApellido="",segundoApellido="";
                 
     
     CuentasBancarias bonc = new CuentasBancarias();
     Propietarios prep = new Propietarios();
     

     do{
         
         System.out.println("==========================");
         System.out.println("1- Insertar una nueva cuenta bancaria");
         System.out.println("2- Entrar en una cuenta bancaria");
         System.out.println("3- Lista de todas las operaciones bancarias");
         System.out.println("4- Lista de operaciones bancarias que han generado números rojos");
         System.out.println("5- Ranking de cuentas en números rojos");
         System.out.println("6- Obtener operación bancaria a partir de su ID");
         System.out.println("0- Salir");
         System.out.println("==========================");
         elec= Datos.entero();
         
         switch(elec){
             case 1:
                 
                 
                 
                 do{
                     System.out.println("Introduce el número de cuenta (formato xxxx-xxxx-xxxx-xxxx)");
                     numCuenta= Datos.cadena();
                     cont= bonc.comprobarNumCuenta(numCuenta); 
                 }while(cont!=0); 
                do{
                    System.out.println("Introduce el DNI (formato xxxxxxxxA)");
                    dni= Datos.cadena();
                    cont= bonc.comprobarDNI(dni);
                }while(cont!=0);
                do{
                    System.out.println("Introduce el Nombre de usuario");
                    usuario= Datos.cadena();
                    cont= prep.comprobarUsuario(usuario);
               }while(cont!=0); 
                do{
                    System.out.println("Introduce el número secreto (4 dígitos)");
                    numSecreto= Datos.cadena();
                    cont= prep.comprobarNumSecreto(numSecreto);
                }while(cont!=0); 
                do{
                    System.out.println("Introduce el nombre");
                    nombre= Datos.cadena();
                    cont= prep.comprobarNombreApellidos(nombre);
                }while(cont!=0);
                do{
                    System.out.println("Introduce el primer apellido");
                    primerApellido= Datos.cadena();
                    cont= prep.comprobarNombreApellidos(primerApellido);
                }while(cont!=0);
                do{
                    System.out.println("Introduce el segundo apellido");
                    segundoApellido= Datos.cadena();
                    cont= prep.comprobarNombreApellidos(segundoApellido);
                 }while(cont!=0);
                 
                 System.out.println("aver");
                         
                 conn.insertarCuenta(numCuenta,dni,usuario,numSecreto,nombre,primerApellido,segundoApellido);
                 
                 System.out.println("aver8");
                 
                 break;
                 
            case 2:
                
                
                do{
                    System.out.println("Introduce el DNI (formato xxxxxxxxA)");
                    dni= Datos.cadena();
                    cont= bonc.comprobarDNI(dni);
                }while(cont!=0); 
                do{
                    System.out.println("Introduce el número secreto (4 dígitos)");
                    numSecreto= Datos.cadena();
                    cont= prep.comprobarNumSecreto(numSecreto);
                }while(cont!=0); 
                
                
                DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now2 = LocalDateTime.now();
                String fecha=  dt.format(now2).toString();
                
               boolean bulean= conn.verificarExistencia(dni, numSecreto, fecha);
            
               if(bulean){
                 
                  do{    
                   System.out.println("-------------------------------");  
                   System.out.println("1- Modificar datos del propietario");
                   System.out.println("2- Cambiar número secreto");
                   System.out.println("3- Eliminar cuenta bancaria");
                   System.out.println("4- Hacer una operación bancaria");
                   System.out.println("5- Ver mis operaciones bancarias");
                   System.out.println("6- Ver fecha de mi último inicio de sesión");
                   System.out.println("7- Ver mi posición en el ranking de cuentas");
                   System.out.println("0- Volver");
                   System.out.println("-------------------------------");
                   elecUsuario= Datos.entero();  
                   
                   switch(elecUsuario){
                       
                       case 1:
                       
                           
                           do{
                             System.out.println("Introduce el nuevo nombre");
                             nombre= Datos.cadena();
                             cont= prep.comprobarNombreApellidos(nombre);
                          }while(cont!=0);
                           do{
                              System.out.println("Introduce el nuevo primer apellido");
                              primerApellido= Datos.cadena();
                              cont= prep.comprobarNombreApellidos(primerApellido);
                            }while(cont!=0);
                           do{
                              System.out.println("Introduce el nuevo segundo apellido");
                              segundoApellido= Datos.cadena();
                              cont= prep.comprobarNombreApellidos(segundoApellido);
                           }while(cont!=0);
                
                           conn.ActualizarDatosPropietario(dni, nombre, primerApellido, segundoApellido);
                           
                           System.out.println("Se han actualizado los datos del propietario correctamente");
                            
                           
                           
                           break;
                       
                       case 2:
                       
                           
                            do{
                             System.out.println("Introduce el nuevo número secreto (4 dígitos)");
                             numSecreto= Datos.cadena();
                             cont= prep.comprobarNumSecreto(numSecreto);
                            }while(cont!=0);
                           
                           conn.ActualizarNumeroSecreto(dni,numSecreto);

                           
                           break;
                       
                       case 3:
                           
                           conn.borrarCuenta(dni);
                           
                           System.out.println("La cuenta bancaria ha sido eliminada");
                           
                           elecUsuario=0;
                           
                           
                           break;
                       
                       case 4:
                       
                           
                           DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                           LocalDateTime now = LocalDateTime.now();
                         
                          String fecha2=  dtf.format(now).toString();
                           
                           System.out.println("e- hacer una extracción");
                            System.out.println("i- hacer un ingreso");
                            
                           String ope= Datos.cadena();
                           
                           if(ope.equals("e")){
                               
                               System.out.println("Introduce la cantidad que quieres extraer");
                               int ext= Datos.entero();
                               
                            conn.hacerOperacion(dni, ext, ope, fecha2);
                               
                               System.out.println("Se ha hecho la extracción correctamente");
                           }
                           else if(ope.equals("i")){
                               
                               System.out.println("Introduce la cantidad que quieres ingresar");
                               int ing= Datos.entero();
                           
                               conn.hacerOperacion(dni, ing, ope, fecha2);
                               
                               System.out.println("Se ha hecho el ingreso correctamente");
                           }
                           else{
                               System.out.println("Tienes que introducir una opción correcta");
                           }
                           
                           
                           break;
                       
                       case 5:
                       
                           numCuenta =conn.sacarNumCuenta(dni);
                           
                           Iterator it=  conn.verOperaciones(numCuenta);
                
                System.out.format("%-4s%-23s%-23s%-7s%-7s","#","NumCuenta","Fecha","Tipo","Cantidad");

                         System.out.println("");
                         System.out.println("============================================================");
                         System.out.println(""); 
                
                 while (it.hasNext())
                {
                    Operaciones a2= (Operaciones)it.next();
      
                         System.out.format("%-4d%-23s%-23s%-7s%-7s\n",
                          a2.getIdOperacion(),
                          a2.getNumCuenta(),
                          a2.getFechaHora(),
                          a2.getTipoOperacion(),
                          a2.getCantidad());

                         System.out.println(""); 
                          
                }
                           
                           
                           break;
                       
                       case 6:
                          System.out.println(conn.verFechaLogin(fecha).getFechaHora());
                           break;
                           
                       case 7:
                           
                           int pos= conn.verMiRanking(dni);
                           
                          System.out.println("Mi posición dentro del ranking de cuentas es "+pos);
                           
                           break;
                 
                   }
                   
                  }while(elecUsuario!=0);
                 
                   break;
               }
               else{
                   System.out.println("El dni o el número secreto son incorrectos");
               }
             case 3:
                Iterator it=  conn.verOperaciones();
                
                System.out.format("%-4s%-23s%-23s%-7s%-7s","#","NumCuenta","Fecha","Tipo","Cantidad");

                         System.out.println("");
                         System.out.println("============================================================");
                         System.out.println(""); 
                
                 while (it.hasNext())
                {
                    Operaciones a2= (Operaciones)it.next();
      
                         System.out.format("%-4d%-23s%-23s%-7s%-7s\n",
                          a2.getIdOperacion(),
                          a2.getNumCuenta(),
                          a2.getFechaHora(),
                          a2.getTipoOperacion(),
                          a2.getCantidad());

                         System.out.println(""); 
                          
                }
                 
                 break;
                 
            case 4:
                 
                Iterator it2=  conn.operacionesGeneradoNumerosRojos();
                
                System.out.format("%-4s%-23s%-23s%-7s%-7s","#","NumCuenta","Fecha","Tipo","Cantidad");

                         System.out.println("");
                         System.out.println("============================================================");
                         System.out.println(""); 
                
                 while (it2.hasNext())
                {
                    Operaciones a2= (Operaciones)it2.next();
      
                         System.out.format("%-4d%-23s%-23s%-7s%-7s\n",
                          a2.getIdOperacion(),
                          a2.getNumCuenta(),
                          a2.getFechaHora(),
                          a2.getTipoOperacion(),
                          a2.getCantidad());

                         System.out.println(""); 
                          
                }
                
                
                 break;
                 
                 
             case 5:
                 
                  Iterator it3=  conn.verNumerosRojos();
                  
                  System.out.format("%-4s%-23s%-15s%-7s%-7s","#","NumCuenta","Propietario","Saldo","DNI");

                  System.out.println("");
                  System.out.println("============================================================");
                  System.out.println(""); 
                 
                  while (it3.hasNext())
                {
                    CuentasBancarias cb= (CuentasBancarias)it3.next();
                    
                    System.out.format("%-4d%-23s%-15s%-7s%-7s\n",
                            cb.getIdCuentaBancaria(),
                            cb.getNumeroCuenta(),
                            cb.getPropietario(),
                            cb.getSaldo(),
                            cb.getDni());

                              System.out.println("");
                }
                  
                 
                 break;
                 
            case 6:
                
                System.out.println("Introduce el ID(#) de la operación");
                 int id= Datos.entero();
                 
                 System.out.format("%-4s%-23s%-23s%-7s%-7s","#","NumCuenta","Fecha","Tipo","Cantidad");

                         System.out.println("");
                         System.out.println("============================================================");
                         System.out.println(""); 
                         
                         if(conn.verOperacion(id)!=null){
                         System.out.format("%-4s%-23s%-23s%-7s%-7s",
                                 conn.verOperacion(id).getIdOperacion(),
                                 conn.verOperacion(id).getNumCuenta(),
                                 conn.verOperacion(id).getFechaHora(),
                                 conn.verOperacion(id).getTipoOperacion(),
                                 conn.verOperacion(id).getCantidad());
                         System.out.println("");
                         }
                
                 break;                 
                 
            case 0: 
                 System.out.println("se ha salido de la aplicación");
                 
         }
         
     }while(elec!=0);
    
    }
    
}
