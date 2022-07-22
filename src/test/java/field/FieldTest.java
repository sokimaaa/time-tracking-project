package field;

import org.assertj.core.api.Assertions;
import org.junit.BeforeClass;
import org.junit.Test;
import timekeeping.my.view.field.*;

public class FieldTest {

    private static FieldBuilder fieldBuilder;

    @BeforeClass
    public static void beforeClass() {
        fieldBuilder = FieldBuilder.getInstance();
    }

    @Test
    public void testCreateField1() {
        Field field = fieldBuilder.createField(FieldEnum.LOGIN);

        Assertions.assertThat(field.getName()).isEqualTo("login");
        Assertions.assertThat(field.getTittle()).isEqualTo("Login");
        Assertions.assertThat(field.getInputType()).isEqualTo(InputType.TEXT);
        Assertions.assertThat(field.getType()).isEqualTo(FieldType.INPUT);
    }

    @Test
    public void testCreateField2() {
        Field field = fieldBuilder.createField(FieldEnum.PASSWORD);

        Assertions.assertThat(field.getName()).isEqualTo("pass");
        Assertions.assertThat(field.getTittle()).isEqualTo("Password");
        Assertions.assertThat(field.getInputType()).isEqualTo(InputType.PASSWORD);
        Assertions.assertThat(field.getType()).isEqualTo(FieldType.INPUT);
    }

    @Test
    public void testCreateField3() {
        fieldBuilder.createField(null);
    }
}
