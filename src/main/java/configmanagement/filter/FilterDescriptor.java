package configmanagement.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class FilterDescriptor implements Serializable {
    private static final long serialVersionUID = 1L;

    @NonNull
    private String field;
    @NonNull
    private Comparison comparison = Comparison.EQUAL;
    private String value;
    private boolean disjunction = false;
    private List<FilterDescriptor> filters = new ArrayList<>();

    @Override
    public String toString() {
        return "" + field + " " + comparison + " " + value;
    }
}
