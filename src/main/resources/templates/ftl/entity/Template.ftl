package ${BASE_PACKAGE}.entity;

import java.io.Serializable;
<#list IMPORT_FULL_JAVA_TYPE as FULL_JAVA_TYPE>
import ${FULL_JAVA_TYPE};
</#list>

/**
 * <p>@author : ${AUTHOR}</p>
 * <p>@date : ${CREATE_DATE}</p>
 * <p>@description : ${REMARKS}</p>
 */
public class ${ENTITY_CLASS_NAME} implements Serializable {

    private static final long serialVersionUID = 1L;
<#list TABLE_COLUMNS as COLUMN>
    /**
     * ${COLUMN.REMARKS}
     */
    private ${COLUMN.JAVA_TYPE} ${COLUMN.ENTITY_PROPERTY_NAME};
</#list>

<#list TABLE_COLUMNS as COLUMN>
    public ${COLUMN.JAVA_TYPE} get${COLUMN.FIRST_UP_ENTITY_PROPERTY_NAME}() {
        return ${COLUMN.ENTITY_PROPERTY_NAME};
    }

    public void set${COLUMN.FIRST_UP_ENTITY_PROPERTY_NAME}(${COLUMN.JAVA_TYPE} ${COLUMN.ENTITY_PROPERTY_NAME}) {
        this.${COLUMN.ENTITY_PROPERTY_NAME} = ${COLUMN.ENTITY_PROPERTY_NAME};
    }

</#list>
    @Override
    public String toString() {
        return "${ENTITY_CLASS_NAME}{" +
<#list TABLE_COLUMNS as COLUMN>
    <#if COLUMN_index==0>
                "${COLUMN.ENTITY_PROPERTY_NAME}='" + ${COLUMN.ENTITY_PROPERTY_NAME} + '\'' +
    <#else>
                ", ${COLUMN.ENTITY_PROPERTY_NAME}='" + ${COLUMN.ENTITY_PROPERTY_NAME} + '\'' +
    </#if>
</#list>
                '}';
    }
}
