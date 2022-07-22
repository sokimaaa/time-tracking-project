package form;

import org.assertj.core.api.Assertions;
import org.junit.BeforeClass;
import org.junit.Test;
import timekeeping.my.view.form.Form;
import timekeeping.my.view.form.FormBuilder;

public class FormTest {

    private static FormBuilder formBuilder;

    @BeforeClass
    public static void globalSetUp() {
        formBuilder = FormBuilder.getInstance();
    }

    @Test
    public void testLoginForm() {
        Form actualForm = formBuilder.getLoginForm();

        Assertions.assertThat(actualForm).isNotNull();
        Assertions.assertThat(actualForm.getId()).isEqualTo("loginForm");
        Assertions.assertThat(actualForm.getButtonText()).isEqualTo("form.button.login");
        Assertions.assertThat(actualForm.getTitle()).isEqualTo("form.title.login");
        Assertions.assertThat(actualForm.getAction()).isEqualTo("/toLogin");
        Assertions.assertThat(actualForm.getMethod()).isEqualTo("post");
    }

    @Test
    public void testRegisterForm() {
        Form actualForm = formBuilder.getRegistrationForm();

        Assertions.assertThat(actualForm).isNotNull();
        Assertions.assertThat(actualForm.getId()).isEqualTo("registerForm");
        Assertions.assertThat(actualForm.getButtonText()).isEqualTo("form.button.register");
        Assertions.assertThat(actualForm.getTitle()).isEqualTo( "form.title.register");
        Assertions.assertThat(actualForm.getAction()).isEqualTo("/toRegister");
        Assertions.assertThat(actualForm.getMethod()).isEqualTo("post");
    }

    @Test
    public void testCalculateForm() {
        Form actualForm = formBuilder.getCalculateForm();

        Assertions.assertThat(actualForm).isNotNull();
        Assertions.assertThat(actualForm.getId()).isEqualTo("calculateForm");
        Assertions.assertThat(actualForm.getButtonText()).isEqualTo("form.button.calculate");
        Assertions.assertThat(actualForm.getTitle()).isEqualTo( "form.title.calculate");
        Assertions.assertThat(actualForm.getAction()).isEqualTo("/calculate?id=");
        Assertions.assertThat(actualForm.getMethod()).isEqualTo("post");
    }

    @Test
    public void testSendRequestForm() {
        Form actualForm = formBuilder.getSendRequestForm();

        Assertions.assertThat(actualForm).isNotNull();
        Assertions.assertThat(actualForm.getId()).isEqualTo("joinForm");
        Assertions.assertThat(actualForm.getButtonText()).isEqualTo("form.button.join");
        Assertions.assertThat(actualForm.getTitle()).isEqualTo( "form.title.join");
        Assertions.assertThat(actualForm.getAction()).isEqualTo("/join?id=");
        Assertions.assertThat(actualForm.getMethod()).isEqualTo("post");
    }

    @Test
    public void testAdminCategoriesForm() {
        Form actualForm = formBuilder.getAdminCategoriesForm();

        Assertions.assertThat(actualForm).isNotNull();
        Assertions.assertThat(actualForm.getId()).isEqualTo("categoriesForm");
        Assertions.assertThat(actualForm.getButtonText()).isEqualTo("form.button.admin.categories");
        Assertions.assertThat(actualForm.getTitle()).isEqualTo( "form.title.admin.categories");
        Assertions.assertThat(actualForm.getAction()).isEqualTo("/add");
        Assertions.assertThat(actualForm.getMethod()).isEqualTo("post");
    }

}
