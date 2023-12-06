package smartbeans.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
	DatahubConfigAspect.class,
	DatahubConfigCommon.class,
	DatahubConfigDatasource.class,
	DatahubConfigIdGeneration.class,
	DatahubConfigMapper.class,
	DatahubConfigProperties.class,
	DatahubConfigTransaction.class,
	DatahubConfigValidator.class,
	EgovConfigAppWhitelist.class
})
public class DatahubConfig {

}
