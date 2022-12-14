package com.teamdev.market.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.teamdev.market.entities.Usuario;
import com.teamdev.market.services.interfaces.IusuarioService;

@Component
public class InfoAditionalToken implements TokenEnhancer {
	
	@Autowired
	private IusuarioService usuarioService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Usuario usuario = usuarioService.getByCorreo(authentication.getName());
		
		Map<String, Object> info = new HashMap<>();
		
		info.put("correo", authentication.getName());
		
		info.put("nombre", usuario.getNombre());
		info.put("apellido", usuario.getApellido());
		info.put("direccion", usuario.getDireccion());
		info.put("id", usuario.getId());
		
		
		((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(info);
		return accessToken;
	}


}
