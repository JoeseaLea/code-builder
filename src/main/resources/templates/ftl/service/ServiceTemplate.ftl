package ${BASE_PACKAGE}.service;

import ${BASE_PACKAGE}.entity.${ENTITY_CLASS_NAME};

import java.util.List;

/**
 * <p>@author : ${AUTHOR}</p>
 * <p>@date : ${CREATE_DATE}</p>
 * <p>@description : </p>
 */
public interface ${ENTITY_CLASS_NAME}Service {
    /**
     * 添加
     * @param ${ENTITY_NAME}
     */
    void add(${ENTITY_CLASS_NAME} ${ENTITY_NAME});

    /**
     * 更新
     * @param ${ENTITY_NAME}
     */
    void update(${ENTITY_CLASS_NAME} ${ENTITY_NAME});

    /**
     * 添加或更新
     * 说明：唯一约束冲突时执行更新操作，否则执行添加
     * @param ${ENTITY_NAME}
     */
    void save(${ENTITY_CLASS_NAME} ${ENTITY_NAME});

<#if (PRIMARY_KEYS?size==1) >
    <#list PRIMARY_KEYS as COLUMN>
    /**
     * 删除
     * @param ${COLUMN.ENTITY_PROPERTY_NAME}
     */
    void delBy${COLUMN.FIRST_UP_ENTITY_PROPERTY_NAME}(${COLUMN.JAVA_TYPE} ${COLUMN.ENTITY_PROPERTY_NAME});
    </#list>
</#if>

<#if (PRIMARY_KEYS?size==1) >
    <#list PRIMARY_KEYS as COLUMN>
    /**
     * 条件查找单个实体
     * @param ${COLUMN.ENTITY_PROPERTY_NAME}
     * @return 查询结果(${ENTITY_CLASS_NAME})
     */
    ${ENTITY_CLASS_NAME} getBy${COLUMN.FIRST_UP_ENTITY_PROPERTY_NAME}(${COLUMN.JAVA_TYPE} ${COLUMN.ENTITY_PROPERTY_NAME});
    </#list>
</#if>

    /**
     * 条件查询所有实体
     * @param ${ENTITY_NAME}
     * @return 查询结果(List<${ENTITY_CLASS_NAME}>)
    */
    List<${ENTITY_CLASS_NAME}> getList(${ENTITY_CLASS_NAME} ${ENTITY_NAME});
}
