<#assign hash_key = "#">
<#assign $_key = "$">
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${BASE_PACKAGE}.dao.${ENTITY_CLASS_NAME}Dao">
    <sql id="common_sql">
    <#list TABLE_COLUMNS as COLUMN>
        <#if TABLE_COLUMNS?size==COLUMN_index+1>
        `${COLUMN.COLUMN_NAME}`
        <#else>
        `${COLUMN.COLUMN_NAME}`,
        </#if>
    </#list>
    </sql>
    <resultMap id="result_map" type="${BASE_PACKAGE}.entity.${ENTITY_CLASS_NAME}">
        <#list TABLE_COLUMNS as COLUMN>
        <result column="${COLUMN.COLUMN_NAME}" property="${COLUMN.ENTITY_PROPERTY_NAME}"/>
        </#list>
    </resultMap>

    <insert id="add" parameterType="${BASE_PACKAGE}.entity.${ENTITY_CLASS_NAME}">
        INSERT INTO `${TABLE_NAME}`(<#list TABLE_COLUMNS as COLUMN><#if TABLE_COLUMNS?size==COLUMN_index+1>`${COLUMN.COLUMN_NAME}`<#else>`${COLUMN.COLUMN_NAME}`, </#if></#list>)
        VALUES (<#list TABLE_COLUMNS as COLUMN><#if TABLE_COLUMNS?size==COLUMN_index+1>${hash_key}{bean.${COLUMN.ENTITY_PROPERTY_NAME}}<#else>${hash_key}{bean.${COLUMN.ENTITY_PROPERTY_NAME}}, </#if></#list>)
    </insert>

    <update id="update" parameterType="${BASE_PACKAGE}.entity.${ENTITY_CLASS_NAME}">
        UPDATE `${TABLE_NAME}`
        SET
        <#list TABLE_COLUMNS as COLUMN>
         <#if TABLE_COLUMNS?size==COLUMN_index+1>
        `${COLUMN.COLUMN_NAME}` = ${hash_key}{bean.${COLUMN.ENTITY_PROPERTY_NAME}}
         <#else>
        `${COLUMN.COLUMN_NAME}` = ${hash_key}{bean.${COLUMN.ENTITY_PROPERTY_NAME}},
         </#if>
        </#list>
        <#list PRIMARY_KEYS as COLUMN>
            <#if PRIMARY_KEYS?size==COLUMN_index+1>
        <where>
            `${COLUMN.COLUMN_NAME}` = ${hash_key}{bean.${COLUMN.ENTITY_PROPERTY_NAME}}
        </where>
            </#if>
        </#list>
    </update>

    <insert id="save" parameterType="${BASE_PACKAGE}.entity.${ENTITY_CLASS_NAME}">
        INSERT INTO `${TABLE_NAME}`(<#list TABLE_COLUMNS as COLUMN><#if TABLE_COLUMNS?size==COLUMN_index+1>`${COLUMN.COLUMN_NAME}`<#else>`${COLUMN.COLUMN_NAME}`, </#if></#list>)
        VALUES (<#list TABLE_COLUMNS as COLUMN><#if TABLE_COLUMNS?size==COLUMN_index+1>${hash_key}{bean.${COLUMN.ENTITY_PROPERTY_NAME}}<#else>${hash_key}{bean.${COLUMN.ENTITY_PROPERTY_NAME}}, </#if></#list>)
        ON DUPLICATE KEY UPDATE
        <#list TABLE_COLUMNS as COLUMN>
         <#if TABLE_COLUMNS?size==COLUMN_index+1>
        `${COLUMN.COLUMN_NAME}` = ${hash_key}{bean.${COLUMN.ENTITY_PROPERTY_NAME}}
         <#else>
        `${COLUMN.COLUMN_NAME}` = ${hash_key}{bean.${COLUMN.ENTITY_PROPERTY_NAME}},
         </#if>
        </#list>
    </insert>

<#list PRIMARY_KEYS as COLUMN>
    <#if PRIMARY_KEYS?size==COLUMN_index+1>
    <delete id="delById">
        DELETE FROM `${TABLE_NAME}`
        <where>
            `${COLUMN.COLUMN_NAME}` = ${hash_key}{bean.${COLUMN.ENTITY_PROPERTY_NAME}}
        </where>
    </delete>
    </#if>
</#list>

<#list PRIMARY_KEYS as COLUMN>
    <#if PRIMARY_KEYS?size==COLUMN_index+1>
    <select id="getById" parameterType="java.lang.String" resultMap="result_map">
        SELECT
        <include refid="common_sql" />
        FROM `${TABLE_NAME}`
        <where>
            `${COLUMN.COLUMN_NAME}` = ${hash_key}{bean.${COLUMN.ENTITY_PROPERTY_NAME}}
        </where>
    </#if>
</#list>
    </select>

    <select id="getList" parameterType="${BASE_PACKAGE}.entity.${ENTITY_CLASS_NAME}" resultMap="result_map">
        SELECT
        <include refid="common_sql" />
        FROM `${TABLE_NAME}`
        <where>
            1=1
         <#list TABLE_COLUMNS as COLUMN>
            <if test="null != param.${COLUMN.ENTITY_PROPERTY_NAME}">
                AND `${COLUMN.COLUMN_NAME}` = ${hash_key}{param.${COLUMN.ENTITY_PROPERTY_NAME}}
            </if>
         </#list>
        </where>
    </select>
</mapper>
