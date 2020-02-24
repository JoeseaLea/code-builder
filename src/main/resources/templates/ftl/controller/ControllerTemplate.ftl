package ${BASE_PACKAGE}.controller;

import com.system.core.common.persistence.result.JsonResult;
import ${BASE_PACKAGE}.entity.${ENTITY_CLASS_NAME};
import ${BASE_PACKAGE}.service.${ENTITY_CLASS_NAME}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>@author : ${AUTHOR}</p>
 * <p>@date : ${CREATE_DATE}</p>
 * <p>@description : </p>
 */
@RestController
@RequestMapping(value = "${ENTITY_NAME}")
public class ${ENTITY_CLASS_NAME}Controller {

    @Autowired
    private ${ENTITY_CLASS_NAME}Service ${ENTITY_NAME}Service;

    @RequestMapping(value = "add")
    public JsonResult add(@RequestBody ${ENTITY_CLASS_NAME} ${ENTITY_NAME}) {
        JsonResult result = new JsonResult();

        ${ENTITY_NAME}Service.add(${ENTITY_NAME});

        result.success();

        return result;
    }

    @RequestMapping(value = "update")
    public JsonResult update(@RequestBody ${ENTITY_CLASS_NAME} ${ENTITY_NAME}) {
        JsonResult result = new JsonResult();

        ${ENTITY_NAME}Service.update(${ENTITY_NAME});

        result.success();

        return result;
    }

    @RequestMapping(value = "save")
    public JsonResult save(@RequestBody ${ENTITY_CLASS_NAME} ${ENTITY_NAME}) {
        JsonResult result = new JsonResult();

        ${ENTITY_NAME}Service.save(${ENTITY_NAME});

        result.success();

        return result;
    }
<#if (PRIMARY_KEYS?size==1) >
    <#list PRIMARY_KEYS as COLUMN>
    @RequestMapping(value = "delBy${COLUMN.FIRST_UP_ENTITY_PROPERTY_NAME}")
    public JsonResult delBy${COLUMN.FIRST_UP_ENTITY_PROPERTY_NAME}(@RequestBody ${COLUMN.JAVA_TYPE} ${COLUMN.ENTITY_PROPERTY_NAME}) {
        JsonResult result = new JsonResult();

        ${ENTITY_NAME}Service.delBy${COLUMN.FIRST_UP_ENTITY_PROPERTY_NAME}(${COLUMN.ENTITY_PROPERTY_NAME});

        result.success();

        return result;
    }
    </#list>
</#if>

<#if (PRIMARY_KEYS?size==1) >
    <#list PRIMARY_KEYS as COLUMN>
    @RequestMapping(value = "getBy${COLUMN.FIRST_UP_ENTITY_PROPERTY_NAME}")
    public JsonResult getBy${COLUMN.FIRST_UP_ENTITY_PROPERTY_NAME}(@RequestBody ${COLUMN.JAVA_TYPE} ${COLUMN.ENTITY_PROPERTY_NAME}) {
        JsonResult result = new JsonResult(${ENTITY_NAME}Service.getBy${COLUMN.FIRST_UP_ENTITY_PROPERTY_NAME}(${COLUMN.ENTITY_PROPERTY_NAME}));
        return result;
    }
    </#list>
</#if>

    @RequestMapping(value = "getList")
    public JsonResult getList(@RequestBody ${ENTITY_CLASS_NAME} ${ENTITY_NAME}) {
        JsonResult result = new JsonResult(${ENTITY_NAME}Service.getList(${ENTITY_NAME}));
        return result;
    }
}
