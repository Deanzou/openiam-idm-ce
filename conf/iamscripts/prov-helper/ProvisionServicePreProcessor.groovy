
import org.openiam.base.AttributeOperationEnum
import org.openiam.idm.srvc.org.dto.Organization
import org.openiam.idm.srvc.role.dto.Role
import org.openiam.idm.srvc.user.dto.UserAttribute
import org.openiam.idm.srvc.user.util.DelegationFilterHelper

import java.util.*;

import org.openiam.provision.dto.PasswordSync;
import org.openiam.provision.dto.ProvisionUser;
import org.openiam.provision.service.ProvisioningConstants;
import org.openiam.provision.service.AbstractProvisionPreProcessor;
import org.openiam.idm.srvc.org.service.OrganizationDataService
import org.openiam.idm.srvc.role.service.RoleDataService;
import org.openiam.idm.srvc.auth.login.LoginDataService;
import org.openiam.idm.srvc.res.service.ResourceDataService;


/**
 * Pre-processor script that is used with the Provisioning service.
 */
public class ProvisionServicePreProcessor extends AbstractProvisionPreProcessor<ProvisionUser> {
    private String ORGANIZATION_ADMIN_ROLEID = "8a4a92c641c017e00141c32e69e002c7";

    public int add(ProvisionUser user, Map<String, Object> bindingMap) {

        // context to look up spring beans - commonly used beans. Included to help development

        OrganizationDataService orgManager = (OrganizationDataService)context.getBean("orgManager");
        RoleDataService roleDataService = (RoleDataService)context.getBean("roleDataService");
        LoginDataService loginService = (LoginDataService)context.getBean("loginManager");
        ResourceDataService resourceDataService = (ResourceDataService)context.getBean("resourceDataService");
        Boolean sendActivationLink = bindingMap.get("sendActivationLink")

        println("ProvisionServicePreProcessor: AddUser called.");
        println("ProvisionServicePreProcessor: User=" + user.toString());
 	println("ProvisionServicePreProcessor: User from acriviti creation =" + user.isFromActivitiCreation);
	user.emailCredentialsToNewUsers = (sendActivationLink!=null && sendActivationLink) ? true: !user.isFromActivitiCreation;
        
	showBindingMap(bindingMap);

        //Add Delegation Filter for selected users org if processed user is in Organization Admin role
        for(Role role : user.getRoles()) {
            if (ORGANIZATION_ADMIN_ROLEID.equals(role.id)) {
                println("Organization Admin");
                Organization organization = user.getPrimaryOrganization();
                if (organization != null) {
                    UserAttribute attr = new UserAttribute(DelegationFilterHelper.DLG_FLT_ORG, organization.getId());
                    attr.setOperation(AttributeOperationEnum.ADD);
                    attr.setId(null);
                    user.getUserAttributes().put(attr.getName(),attr);
                }
            }
        }

        /*
        //Try to fix login
	println("-----------------------------------------try fix LOGIN --------------------------------------");
	println ("Login from Primary Principal" + user.getPrimaryPrincipal("0"));
        println ("Login from principalList" + user.principalList);
	Login l = user.getPrimaryPrincipal("0");
	if (l){
		println("-------------------------------------------- fix LOGIN --------------------------------------");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, 120);
		l.pwdExp = c.getTime();
		
		
	} */

        return ProvisioningConstants.SUCCESS;
    }

    public int modify(ProvisionUser user, Map<String, Object> bindingMap){
        // context to look up spring beans

        println("ProvisionServicePreProcessor: ModifyUser called.");
        println("ProvisionServicePreProcessor: User=" + user.toString());

        showBindingMap(bindingMap);





        return ProvisioningConstants.SUCCESS;

    }



    public int delete(ProvisionUser user, Map<String, Object> bindingMap){

        // context to look up spring beans

        println("ProvisionServicePreProcessor: DeleteUser called.");
        println("ProvisionServicePreProcessor: User=" + user.toString());

        showBindingMap(bindingMap);



        return ProvisioningConstants.SUCCESS;
    }

    public int setPassword( PasswordSync passwordSync, Map<String, Object> bindingMap){



        println("ProvisionServicePreProcessor: SetPassword called.");

        showBindingMap(bindingMap);


        return ProvisioningConstants.SUCCESS;

    }

    private void showBindingMap( Map<String, Object> bindingMap){
        // context to look up spring beans
        println("Show binding map:");
        for (Map.Entry<String, Object> entry : bindingMap.entrySet()) {
            def key = entry.key
            def val = entry.value as String
            if (key == 'password') {
                val = 'PROTECTED'
            }
            println("- Key=" + key + " value=" + val)
        }
    }

}
