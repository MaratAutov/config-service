package configmanagement.controller;

import configmanagement.domain.Parameter;
import configmanagement.service.ConfigServiceManagemt;
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
@RequestMapping("/config")
@Api(value = "Сервис управления конфигурационными параметрами", description = "Сервис управления конфигурационными параметрами")
public class Controller {

    private final ConfigServiceManagemt service;

    @Autowired
    public Controller(ConfigServiceManagemt service) {
        this.service = service;
    }

    /**
     * @param parameter
     */
    @PostMapping("/parameters")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Добавить параметр")
    public void addParameter(@ApiParam(value = "Добавляемый параметр") @RequestBody Parameter parameter) {
        service.addParameter(parameter);
    }

    /**
     * @return
     */
    @GetMapping("/parameters")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Parameter> getParameters() {
        return service.getParameters();
    }

    /**
     * Получить параметр по идентификатору
     *
     * @param id
     * @return
     */
    @GetMapping("/parameters/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ApiOperation(value = "Получить параметр по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Параметр найден")
    })
    public Parameter getParameterById(@ApiParam(value = "Идентификатор параметра") @PathVariable Integer id) {
        return service.getParameterById(id);
    }

    @PutMapping("/parameters")
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
    @DeleteMapping("/parameters/{id}")
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
