package com.yibo.parking.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenctiationSuccessHandler authenctiationSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();

        http.formLogin()
                .loginPage("/login")			//登陆界面，用于输入用户和密码。跟控制器login中返回的名称相同
                .loginProcessingUrl("/loginProcess")	//登陆界面提交表单的action对应的名称
                .defaultSuccessUrl("/")		//验证成功后默认的跳转动作
                .failureUrl("/login")		//验证失败后跳转的动作
                .successHandler(authenctiationSuccessHandler)
                .permitAll()				//表示这个不需要验证登录页面/登录失败页面
                .and()
            .sessionManagement()
                .invalidSessionUrl("/login")//session失效后跳转路径
                .and()
            .authorizeRequests()
                .antMatchers("/assets/*","/lib/*","/static/*","/css/*","/image/*","/img/*","/font/*","/js/*","/Widget/*").permitAll()
                .antMatchers("/api/*").permitAll()
                .antMatchers("*/upload").permitAll()
                .antMatchers("/uploadFiles/*").permitAll()
                .antMatchers("/ValidateCode").permitAll()
                .antMatchers(HttpMethod.POST).hasRole("ADMIN")
                //.antMatchers("/index").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .and()
            .csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/assets/**");
        web.ignoring().antMatchers("/lib/**");
        web.ignoring().antMatchers("/static/**");
        web.ignoring().antMatchers("/css/**");
        web.ignoring().antMatchers("/images/**");
        web.ignoring().antMatchers("/img/**");
        web.ignoring().antMatchers("/font/**");
        web.ignoring().antMatchers("/js/**");
        web.ignoring().antMatchers("/Widget/**");
        web.ignoring().antMatchers("/uploadFiles/**");
    }

    @Autowired
    private SecurityAuthenticationProvider provider;//注入我们自己的AuthenticationProvider

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //从内存中获取
        //auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("user").password(new BCryptPasswordEncoder().encode("123456")).roles("admin");
        //从接口中获取
        //auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
        auth.authenticationProvider(provider);
    }
}
