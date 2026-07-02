package com.example.essentials;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 웹 서버(Web server), WAS, 톰캣(Tomcat) 이란?

// [웹 서버]
// 먼저 '웹 서버'는 브라우저의 요청을 받아 이미 만들어져 있는 정적인 파일(html, css, 이미지 등)을 그대로 전달해주는 서버이다.
// - 대표적인 웹 서버가 바로 아파치(Apache HTTP Server)와 Nginx다.
// - 정적 파일 전달, HTTPS 처리, 요청 분배(로드 밸런싱) 같은 일은 잘하지만,
// 자바 코드를 직접 실행해 결과를 만들어 내지는 못한다.

// [WAS(Web Application Server)]
// 사용자가 브라우저로 요청을 보내면, 그 요청을 받아 우리가 작성한 자바 코드를 실행하고
// 그 결과(HTML, JSON 등)을 다시 응답으로 돌려주는 '실행 환경'을 제공하는 서버를 WAS라고 한다.
// - 즉 웹 서버가 못 하는 '동적인' 처리를 요청마다 프로그램을 돌려 만들어 낸다.
// - 요청/응답 처리, 스레드 관리, 세션 관리 같은 서버 공통 기능을 대신 맡아 준다.
// - 실무에서는 아파치(웹 서버)를 앞단에 두어 정적 요소를 처리하고,
// 동적 요청만 뒷단의 WAS로 전달하는 구조를 많이 사용한다.
// - 대표적인 WAS가 바로 톰캣(Tomcat)이다.
// 톰캣(Tomcat)은 가장 널리 사용되는 오픈 소스 WAS로, 자바 웹 애플리케이션을 실행할 수 있는 환경을 제공한다.

// [톰캣]
// 자바의 서블릿(Servlet) 규격을 따르며, 스프링 MVC도 결국 이 서블릿 위에서 동작한다.
// - 톰캣은 정적 파일도 어느 정도 다룰 수 있어, 규모가 작으면 웹 서버 없이 톰캣만으로도 서비스가 가능하다.
// - 원래는 톰캣을 따로 설치하고 그 안에 우리 애플리케이션(WAR)을 넣어 실행했다.
// - 스프링부트(Spring Boot)는 톰캣을 라이브러리 형태로 애플리케이션 안에 내장(embedded)하여,
// 우리가 따로 설치하지 않아도 main() 실행만으로 서버가 함께 떠서 요청을 받을 수 있다.

@SpringBootApplication
public class EssentialsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EssentialsApplication.class, args);
	}

}
