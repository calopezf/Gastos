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
public enum EnumTipoParametroMaestroProceso {

	
	TIPO,TIPO_GASTO;
	
	public static List<EnumTipoParametroMaestroProceso> getTipoParametroEnumList() {
		return Arrays.asList(EnumTipoParametroMaestroProceso.values());
	}
}
