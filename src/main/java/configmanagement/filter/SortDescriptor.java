package configmanagement.filter;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SortDescriptor implements Serializable {

    private static final long serialVersionUID = 1L;

    private String field;
    private Direction direction = Direction.ASC;

    public enum Direction {
        ASC,
        DESC
    }

}
