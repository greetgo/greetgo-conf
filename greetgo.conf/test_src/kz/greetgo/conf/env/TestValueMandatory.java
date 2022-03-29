package kz.greetgo.conf.env;

import kz.greetgo.conf.hot.Description;

@Description("Description of Test2Config")
public interface TestValueMandatory {

  @Description("test value")
  @EnvName("TEST_VALUE")
  String testValue();

}
