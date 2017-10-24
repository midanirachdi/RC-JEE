package tn.esprit.filters;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

import tn.esprit.authorization.AllowTo;

@Provider
public class AccesDynamicFeature implements DynamicFeature  {
/* if u get an import error : 
	go to window > preferences > jax-RS validator > disable it*/
	@Override
	public void configure(ResourceInfo resourceInfo, FeatureContext context) {
	    if (resourceInfo.getResourceMethod().isAnnotationPresent(AllowTo.class)) {
	    	AllowTo annotation = resourceInfo.getResourceMethod().getAnnotation(AllowTo.class);
	        context.register(new CostumFilter(annotation.roles()));}
		}


}
