
package ar.com.osde.sujetovip.services;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para processStatus.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="processStatus"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="STARTED"/&gt;
 *     &lt;enumeration value="RUNNING"/&gt;
 *     &lt;enumeration value="FINISHED"/&gt;
 *     &lt;enumeration value="CANCELED"/&gt;
 *     &lt;enumeration value="ERROR"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "processStatus")
@XmlEnum
public enum ProcessStatus {

    STARTED,
    RUNNING,
    FINISHED,
    CANCELED,
    ERROR;

    public String value() {
        return name();
    }

    public static ProcessStatus fromValue(String v) {
        return valueOf(v);
    }

}
