/**
 * 
 */
package id.co.sigma.common.server.lov;

import id.co.sigma.common.data.CoreLibLookup;
import id.co.sigma.common.data.approval.CommonApprovalDefinition;
import id.co.sigma.common.data.lov.CommonLOV;
import id.co.sigma.common.data.lov.StrongTypedCustomLOVID;
import id.co.sigma.common.data.query.SimpleSortArgument;

import java.util.Date;

/**
 * bean untuk lov object type dari common approval definition
 * @author <a href="mailto:gede.wibawa@sigma.co.id">Agus Gede Adipartha Wibawa</a>
 * @since Sep 25, 2013 3:14:01 PM
 */
public class CommonApprovalDefinitionLOVProvider extends GenericAuditTrailedObjectCustomLOVProvider<String, CommonApprovalDefinition> {

	@Override
	protected Class<CommonApprovalDefinition> getLOVSourceClass() {
		return CommonApprovalDefinition.class;
	}

	@Override
	protected SimpleSortArgument[] getLOVSorter() {
		return new SimpleSortArgument[] {new SimpleSortArgument("objectName", true)};
	}

	@Override
	protected CommonLOV translateToCommonLovData(CommonApprovalDefinition data) {
		CommonLOV lov = new CommonLOV();
		lov.setParentId("common_approval_definition");
		lov.setLabel(data.getObjectName());
		lov.setDataValue(data.getId());
		return lov;
	}

	@Override
	public StrongTypedCustomLOVID getType() {
		return CoreLibLookup.approvalDefinitionTypes;
	}
	
	@Override
	public String getVersion() {
		return "" + new Date();
	}

}
