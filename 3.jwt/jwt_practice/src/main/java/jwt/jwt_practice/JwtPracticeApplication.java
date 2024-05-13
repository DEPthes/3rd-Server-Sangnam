package jwt.jwt_practice;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.Key;
import java.util.Date;

@SpringBootApplication
public class JwtPracticeApplication {

	public static void main(String[] args) {
		// Secret key
		Key key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);

		// JWT를 생성하기 위한 빌더를 반환합니다.
		JwtBuilder builder = Jwts.builder();

		// JWT의 subject(주제)를 설정합니다. 주로 사용자의 식별자가 됩니다. (필수 항목)
		builder.setSubject("user123");

		// JWT의 issuer(발급자)를 설정합니다. JWT를 발행한 엔터티의 식별자입니다. (선택 항목)
		builder.setIssuer("myApp");

		// JWT의 audience(대상자)를 설정합니다. JWT가 발급되는 대상자를 지정합니다. (선택 항목)
		builder.setAudience("myClient");

		// JWT의 발급 시간을 설정합니다. (필수 항목)
		builder.setIssuedAt(new Date());

		// JWT의 만료 시간을 설정합니다. (선택 항목)
		builder.setExpiration(new Date(System.currentTimeMillis() + 3600000)); // 1 hour later

		// JWT의 활성화 시간을 설정합니다. 이 시간 이전에는 JWT를 사용할 수 없습니다. (선택 항목)
		builder.setNotBefore(new Date(System.currentTimeMillis() - 1000)); // 1 second ago

		// JWT의 고유 식별자를 설정합니다. (선택 항목)
		builder.setId("jwt123");

		// JWT에 서명을 추가합니다. SignatureAlgorithm과 Key를 인자로 받습니다. (필수 항목)
		builder.signWith(key);

		// JWT를 문자열로 직렬화합니다.
		// 결과는 매번다름
		String jwtString = builder.compact();
		System.out.println("JWT: " + jwtString);

		// JWT 문자열을 파싱하여 Jws<Claims> 객체를 반환합니다.
		Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwtString);
		System.out.println("Parsed JWT: " + claimsJws);

		// 헤더에는 알고리즘
		// Parsed JWT: header={alg=HS256},

		// 바디에는 유저정보, 발급자, 대상자, 발급시간, 만료시간, 활성시간, 식별자
		// body={sub=user123, iss=myApp, aud=myClient, iat=1708965415,
		// exp=1708969015, nbf=1708965414, jti=jwt123},

		// 마지막으로 서명이 추가된다. (매번다름)
		// signature=jNRdFOECIIE8j3MjWeiD6YKWwAdHniPaK_sdXFhlGrI


		// 파싱된 JWT의 클레임을 가져와서 사용합니다.
		Claims claims = claimsJws.getBody();
		String subject = claims.getSubject();
		String issuer = claims.getIssuer();
		Date expiration = claims.getExpiration();

		// JWT의 클레임 정보를 출력합니다.
		// 사용자 정보
		System.out.println("Subject: " + subject);
		// 발급자
		System.out.println("Issuer: " + issuer);
		// 만료일
		System.out.println("Expiration: " + expiration);


		// Subject: user123
		// Issuer: myApp
		// Expiration: Tue Feb 27 02:36:55 KST 2024

	}

}
