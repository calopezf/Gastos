/**
 * UsuarioservicioImpl.java
 * 
* Thu Sep 26 17:17:42 ECT 2013
 */
package ec.edu.puce.professorCheck.servicio;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ec.edu.puce.professorCheck.crud.ServicioCrud;

@Stateless(name = "ParametroMaestroSistema")
@LocalBean
public class ServicioParametroMaestroSistema {

@EJB
private ServicioCrud servicioCrud;
    

}