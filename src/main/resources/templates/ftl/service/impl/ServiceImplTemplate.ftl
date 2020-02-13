package ${BASE_PACKAGE}.service.impl;

import ${BASE_PACKAGE}.dao.${ENTITY_CLASS_NAME}Dao;
import ${BASE_PACKAGE}.entity.${ENTITY_CLASS_NAME};
import ${BASE_PACKAGE}.service.${ENTITY_CLASS_NAME}Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/**
 * <p>@author : ${AUTHOR}</p>
 * <p>@date : ${CREATE_DATE}</p>
 * <p>@description : </p>
 */
@Service
public class ${ENTITY_CLASS_NAME}ServiceImpl implements ${ENTITY_CLASS_NAME}Service {

    @Autowired
    private ${ENTITY_CLASS_NAME}Dao ${ENTITY_CLASS_NAME}Dao;

    @Override
    public void add(${ENTITY_CLASS_NAME} ${ENTITY_NAME}) {
//        if (StringUtils.isBlank(${ENTITY_NAME}.getId())) {
//            ${ENTITY_NAME}.setId(UUID.randomUUID().toString().replaceAll("-", ""));
//        }
//        ${ENTITY_NAME}.setCreateTime(Calendar.getInstance().getTime());

        ${ENTITY_CLASS_NAME}Dao.add(${ENTITY_NAME});
    }

    @Override
    public void update(${ENTITY_CLASS_NAME} ${ENTITY_NAME}) {
        ${ENTITY_CLASS_NAME}Dao.update(${ENTITY_NAME});
    }

    @Override
    public void save(${ENTITY_CLASS_NAME} ${ENTITY_NAME}) {
//        if (StringUtils.isBlank(${ENTITY_NAME}.getId())) {
//            ${ENTITY_NAME}.setId(UUID.randomUUID().toString().replaceAll("-", ""));
//        }
//        ${ENTITY_NAME}.setCreateTime(Calendar.getInstance().getTime());

        ${ENTITY_CLASS_NAME}Dao.save(${ENTITY_NAME});
    }

<#if (PRIMARY_KEYS?size==1) >
    <#list PRIMARY_KEYS as COLUMN>
    @Override
    public void delBy${COLUMN.FIRST_UP_ENTITY_PROPERTY_NAME}(${COLUMN.JAVA_TYPE} ${COLUMN.ENTITY_PROPERTY_NAME}) {
        ${ENTITY_CLASS_NAME}Dao.delBy${COLUMN.FIRST_UP_ENTITY_PROPERTY_NAME}(${COLUMN.ENTITY_PROPERTY_NAME});
    }
    </#list>
</#if>

<#if (PRIMARY_KEYS?size==1) >
    <#list PRIMARY_KEYS as COLUMN>
    @Override
    public ${ENTITY_CLASS_NAME} getBy${COLUMN.FIRST_UP_ENTITY_PROPERTY_NAME}(${COLUMN.JAVA_TYPE} ${COLUMN.ENTITY_PROPERTY_NAME}) {
        return ${ENTITY_CLASS_NAME}Dao.getBy${COLUMN.FIRST_UP_ENTITY_PROPERTY_NAME}(${COLUMN.ENTITY_PROPERTY_NAME});
    }
    </#list>
</#if>

    @Override
    public List<${ENTITY_CLASS_NAME}> getList(${ENTITY_CLASS_NAME} ${ENTITY_NAME}) {
    return ${ENTITY_CLASS_NAME}Dao.getList(${ENTITY_NAME});
    }
}
