package Learning.SpringBoot;

import Learning.Config.DatabaseProperties;

public @interface EnableConfigurationProperties {

    Class<DatabaseProperties> value();

}
