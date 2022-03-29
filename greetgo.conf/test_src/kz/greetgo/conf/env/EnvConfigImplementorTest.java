package kz.greetgo.conf.env;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EnvConfigImplementorTest {

  @Test
  public void impl() {

    EnvSource envSource = name -> "MYBPM_AUX1_PORT".equals(name) ? "7788" : "[[" + name + "]]";

    //
    //
    TestConfig config = EnvConfigImplementor.impl(TestConfig.class, envSource);
    //
    //

    assertThat(config).isInstanceOf(TestConfig.class);
    assertThat(config.dbName()).isEqualTo("[[MYBPM_AUX1_DB_NAME]]");
    assertThat(config.host()).isEqualTo("[[MYBPM_AUX1_HOST]]");
    assertThat(config.username()).isEqualTo("[[MYBPM_AUX1_USER_NAME]]");
    assertThat(config.password()).isEqualTo("[[MYBPM_AUX1_PASSWORD]]");

    assertThat(config.url()).isEqualTo("jdbc:postgresql://[[MYBPM_AUX1_HOST]]:7788/[[MYBPM_AUX1_DB_NAME]]");

  }

  @Test
  public void impl__IllegalEnvValues() {

    EnvSource envSource = name -> "asd";

    IllegalEnvValues error = null;

    try {

      //
      //
      EnvConfigImplementor.impl(TestConfig.class, envSource);
      //
      //

    } catch (IllegalEnvValues e) {
      error = e;
    }

    assertThat(error).isNotNull();
    error.printStackTrace();

  }

  @Test
  public void impl__IllegalEnvValues_2() {

    EnvSource envSource = name -> null;

    IllegalEnvValues error = null;

    try {

      //
      //
      EnvConfigImplementor.impl(TestConfig.class, envSource);
      //
      //

    } catch (IllegalEnvValues e) {
      error = e;
    }

    assertThat(error).isNotNull();
    error.printStackTrace();

  }

  @Test
  public void impl__EnvOptional__null() {

    EnvSource envSource = name -> null;

    //
    //
    TestValueOptional impl = EnvConfigImplementor.impl(TestValueOptional.class, envSource);
    //
    //

    assertThat(impl.testValue()).isNull();

  }

  @Test
  public void impl__EnvOptional__empty() {

    EnvSource envSource = name -> "";

    //
    //
    TestValueOptional impl = EnvConfigImplementor.impl(TestValueOptional.class, envSource);
    //
    //

    assertThat(impl.testValue()).isEqualTo("");

  }

  @Test
  public void impl__EnvMandatory__null() {

    EnvSource envSource = name -> null;

    RuntimeException error = null;
    try {
      //
      //
      EnvConfigImplementor.impl(TestValueMandatory.class, envSource);
      //
      //
    } catch (RuntimeException e) {
      error = e;
    }

    assertThat(error).isNotNull();
    assertThat(error.getMessage()).contains("TEST_VALUE");
    assertThat(error.getMessage()).contains("TestValueMandatory");

  }

  @Test
  public void impl__EnvMandatory__empty() {

    EnvSource envSource = name -> "";

    RuntimeException error = null;
    try {
      //
      //
      EnvConfigImplementor.impl(TestValueMandatory.class, envSource);
      //
      //
    } catch (RuntimeException e) {
      error = e;
    }

    assertThat(error).isNotNull();
    assertThat(error.getMessage()).contains("TEST_VALUE");
    assertThat(error.getMessage()).contains("TestValueMandatory");

  }

}
