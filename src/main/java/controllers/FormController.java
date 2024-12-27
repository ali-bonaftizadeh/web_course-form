package controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import models.Field;
import models.Form;
import org.springframework.web.bind.annotation.*;
import services.FormService;

import java.util.List;


@RestController
@RequestMapping("/forms")
@Api(tags = "Forms", description = "Operations related to managing forms")
public class FormController {

    private final FormService formService;

    public FormController(FormService formService) {
        this.formService = formService;
    }

    @GetMapping
    @ApiOperation(value = "Get all forms", notes = "Retrieve a list of all forms")
    public List<Form> getAllForms() {
        return formService.getAllForms();
    }

    @PostMapping
    @ApiOperation(value = "Create a new form", notes = "Create a new form with the specified details")
    public Form createForm(
            @ApiParam(value = "Form object to be created", required = true)
            @RequestBody Form form) {
        return formService.createForm(form);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get form by ID", notes = "Retrieve a form by its unique ID")
    public Form getForm(
            @ApiParam(value = "ID of the form to be retrieved", required = true)
            @PathVariable Long id) {
        return formService.getForm(id);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a form", notes = "Update an existing form by ID")
    public Form updateForm(
            @ApiParam(value = "ID of the form to be updated", required = true)
            @PathVariable Long id,
            @ApiParam(value = "Updated form details", required = true)
            @RequestBody Form form) {
        return formService.updateForm(id, form);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a form", notes = "Delete a form by its ID")
    public void deleteForm(
            @ApiParam(value = "ID of the form to be deleted", required = true)
            @PathVariable Long id) {
        formService.deleteForm(id);
    }

    @PostMapping("/{id}/publish")
    @ApiOperation(value = "Publish a form", notes = "Change the form's status to published")
    public void publishForm(
            @ApiParam(value = "ID of the form to be published", required = true)
            @PathVariable Long id) {
        formService.publishForm(id);
    }

    @GetMapping("/{id}/fields")
    @ApiOperation(value = "Get all fields of a form", notes = "Retrieve all fields associated with a given form ID")
    public List<Field> getFieldsForForm(
            @ApiParam(value = "ID of the form whose fields are to be retrieved", required = true)
            @PathVariable Long id) {
        return formService.getFieldsForForm(id);
    }
}
