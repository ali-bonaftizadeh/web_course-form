package webcourse.form.controllers;

import webcourse.form.dto.FormDto;
import webcourse.form.dto.FieldDto;
import webcourse.form.dto.FormRequestDto;
import webcourse.form.services.FormService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/forms")
public class FormController {

    private final FormService formService;

    public FormController(FormService formService) {
        this.formService = formService;
    }

    @GetMapping
    public List<FormDto> getAllForms() {
        return formService.getAllForms();
    }

    @PostMapping
    public FormDto createForm(@RequestBody FormRequestDto formDto) {
        return formService.createForm(formDto);
    }

    @GetMapping("/{id}")
    public FormDto getForm(@PathVariable Long id) {
        return formService.getForm(id);
    }

    @PutMapping("/{id}")
    public FormDto updateForm(@PathVariable Long id, @RequestBody FormRequestDto formDto) {
        return formService.updateForm(id, formDto);
    }

    @DeleteMapping("/{id}")
    public void deleteForm(@PathVariable Long id) {
        formService.deleteForm(id);
    }

    @GetMapping("/{id}/fields")
    public List<FieldDto> getFieldsForForm(@PathVariable Long id) {
        return formService.getFieldsForForm(id);
    }

    @PostMapping("/{id}/publish")
    public void publishForm(@PathVariable Long id) {
        formService.publishForm(id);
    }

    @GetMapping("/published")
    public List<FormDto> getPublishedForms() {
        return formService.getAllPublishedForms();
    }
}
