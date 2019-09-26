package cn.org.assetcloud.system;

import org.springblade.common.constant.ApplConstant;
import org.springblade.core.launch.BladeApplication;
import org.springblade.core.launch.constant.AppConstant;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * System启动器
 *
 * @author hjhu
 */
@SpringCloudApplication
@EnableFeignClients({AppConstant.BASE_PACKAGES, ApplConstant.BASE_PACKAGES})
public class AssetSystemApplication {

	public static void main(String[] args) {
		BladeApplication.run(ApplConstant.APPLICATION_SYSTEM_NAME, AssetSystemApplication.class, args);
	}

}

