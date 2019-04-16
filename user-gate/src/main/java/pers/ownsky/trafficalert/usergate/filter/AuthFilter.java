package pers.ownsky.trafficalert.usergate.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.ownsky.trafficalert.publicutils.json.RestException;
import pers.ownsky.trafficalert.usergate.service.UserAuthService;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthFilter extends ZuulFilter {

    private final UserAuthService userAuthService;
    private final UserUnauthConfig userUnauthConfig;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        String uri = request.getRequestURI();
        return !userUnauthConfig.unauthable(uri);
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String token = request.getHeader("auth-token");
        String phone = null;
        try {
            phone = userAuthService.check(token).getBody();
        } catch (RestException e) {
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(e.getStatus().value());
            context.setResponseBody(e.getMessage());
            return null;
        }
        request.setAttribute("phone", phone);
        context.setRequest(request);
        return null;
    }
}
