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

	
	SISTEMA_URL_ENTRADA, SISTEMA_URL_SALIDA, BDD_SERVIDOR, BDD_USUARIO, BDD_PSS, BDD_NOMBRE, EMAIL, URL_DOCUMENTOS;
	
	public static List<TipoParametroMaestroSistema> getTipoParametroEnumList() {
		return Arrays.asList(TipoParametroMaestroSistema.values());
	}
}
