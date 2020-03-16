package configmanagement.controllers;

import configmanagement.domain.Parameter;
import configmanagement.services.ParameterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config/parameters")
@Api(value = "Сервис управления конфигурационными параметрами", description = "Сервис управления конфигурационными параметрами")
public class ParameterController {

    private final ParameterService service;

    @Autowired
    public ParameterController(ParameterService service) {
        this.service = service;
    }

    /**
     * @param parameter
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Добавить параметр")
    public ResponseEntity<Parameter> addParameter(
            @ApiParam(value = "Добавляемый параметр") @RequestBody Parameter parameter) {
        service.addParameter(parameter);
        return ResponseEntity.ok(service.addParameter(parameter));
    }

    /**
     * @return
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<List<Parameter>> getParameters() {
        return ResponseEntity.ok(service.getParameters());
    }

    /**
     * Получить параметр по идентификатору
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ApiOperation(value = "Получить параметр по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Параметр найден"),
            @ApiResponse(code = 404, message = "Параметр не найден")
    })
    public ResponseEntity<Parameter> getParameterById(
            @ApiParam(value = "Идентификатор параметра") @PathVariable Integer id) {
        Parameter parameter = service.getParameterById(id);
        if (parameter != null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(parameter);
        }
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public ResponseEntity<Parameter> updateParameter(@RequestBody Parameter parameter) {
        return ResponseEntity.ok(service.updateParameter(parameter));
    }

    /**
     * Удалить параметр по идентификатору
     *
     * @param id идентификатор параметра
     * @return
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ApiOperation(value = "Удалить параметр по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Удаление произведено"),
            @ApiResponse(code = 404, message = "Параметр не найден")
    })
    public ResponseEntity<?> deleteParameterById(
            @ApiParam(value = "Идентификатор параметра") @PathVariable Integer id) {
        if (service.deleteParameterById(id)) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }

}
