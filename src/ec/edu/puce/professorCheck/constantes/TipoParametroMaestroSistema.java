/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.puce.professorCheck.constantes;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author cristian
 */
public enum TipoParametroMaestroSistema {

	
	SISTEMA__URL_ENT, SISTEMA_URL_SAL, BDD_URL,BDD_USR, BDD_PSS, BDD_NOM, MAIL, URL_DOCUMENTOS, 
	PLAN_ESTUDIOS, SEMESTRE, OCUPACION_PROFESOR, NIVEL_ALUMNO, AREA_MATERIA, CARRERA;

	public static List<TipoParametroMaestroSistema> getTipoParametroEnumList() {
		return Arrays.asList(TipoParametroMaestroSistema.values());
	}
}
