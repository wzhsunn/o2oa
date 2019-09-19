package com.x.file.assemble.control.jaxrs.attachment2;

import com.x.base.core.container.EntityManagerContainer;
import com.x.base.core.container.factory.EntityManagerContainerFactory;
import com.x.base.core.project.http.ActionResult;
import com.x.base.core.project.http.EffectivePerson;
import com.x.base.core.project.jaxrs.WoId;
import com.x.file.assemble.control.Business;
import com.x.file.core.entity.open.OriginFile;

class ActionCheckFileExist extends BaseAction {

	ActionResult<Wo> execute(EffectivePerson effectivePerson, String fileMd5) throws Exception {
		try (EntityManagerContainer emc = EntityManagerContainerFactory.instance().create()) {
			Business business = new Business(emc);
			ActionResult<Wo> result = new ActionResult<>();
			OriginFile originFile = business.originFile().getByMd5(fileMd5);
			Wo wo = new Wo();
			if(originFile!=null) {
				wo.setId(originFile.getId());
			}
			result.setData(wo);
			return result;
		}
	}

	public static class Wo extends WoId {

	}
}
