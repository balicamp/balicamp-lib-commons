package id.co.sigma.common.server.service.dualcontrol;

public interface ICustomBulkFieldIntegrityValidatorManager {
	
	
	public <D, T extends ICustomBulkFieldIntegrityValidator<D>> void register(T handler);

}
