package configmanagement.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString
@ApiModel(description = "Подписка")
public class Subscription {
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    @ApiModelProperty(notes = "Идентификатор", dataType = "Integer")
    private Integer id;
    @ApiModelProperty(notes = "Версия", dataType = "Integer")
    private Integer version = 1;
    @EqualsAndHashCode.Exclude
    @ApiModelProperty(notes = "Наименование подписки", dataType = "String")
    private String name;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ApiModelProperty(notes = "Описание подписки", dataType = "String")
    private String description;

    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ApiModelProperty(notes = "Список подписантов, которые подписаны на данную подписку")
    private List<Subscriber> subscribers = new ArrayList<>();

    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ApiModelProperty(notes = "Список параметров, входящих в данную подписку")
    private List<Parameter> parameters = new ArrayList<>();
}
