package configmanagement.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
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
@ApiModel(description = "Конфигурационный параметр")
public class Parameter implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "Идентификатор", dataType = "Integer")
    private Integer id;
    @ApiModelProperty(notes = "Версия", dataType = "Integer")
    private Integer version = 0;
    @EqualsAndHashCode.Exclude
    @ApiModelProperty(notes = "Наименование параметра", dataType = "String")
    private String name;
    @EqualsAndHashCode.Exclude
    @ApiModelProperty(notes = "Значение параметра", dataType = "String")
    private String value;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ApiModelProperty(notes = "Описание параметра", dataType = "String")
    private String description;

    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ApiModelProperty(notes = "Список подписок, в которые входит данный параметр")
    private List<Subscription> subscriptions = new ArrayList<>();
}
