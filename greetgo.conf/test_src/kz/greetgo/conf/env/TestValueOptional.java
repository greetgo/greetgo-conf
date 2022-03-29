package kz.greetgo.conf.env;

import kz.greetgo.conf.hot.Description;

@Description("Description of Test2Config")
public interface TestValueOptional {

  @Description("test value")
  @EnvOptional
  @EnvName("TEST_VALUE")
  String testValue();

}
