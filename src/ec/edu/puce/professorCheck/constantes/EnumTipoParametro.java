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
public enum EnumTipoParametro {

	
	SISTEMA__URL_ENT, SISTEMA_URL_SAL, BDD_URL,BDD_USR, BDD_PSS, BDD_NOM, MAIL, URL_DOCUMENTOS;

	public static List<EnumTipoParametro> getTipoParametroEnumList() {
		return Arrays.asList(EnumTipoParametro.values());
	}
}
