package com.foreignexchangeapplicaton.restwebservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RestWebServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	public void exchangeRateTest() throws Exception {
		mockMvc.perform( MockMvcRequestBuilders
				.get("/exchangeRate")
				.queryParam("fromRate", "EUR")
				.queryParam("toRate", "TRY")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.exchangeRate").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.exchangeRate").isNumber());
	}

	@Test
	public void exchangeRateTestWithNoParamError() throws Exception {
		mockMvc.perform( MockMvcRequestBuilders
				.get("/exchangeRate")
				.queryParam("fromRate", "EUR")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().is5xxServerError());
	}

	@Test
	public void exchangeRateTestWithNotFoundError() throws Exception {
		mockMvc.perform( MockMvcRequestBuilders
				.get("/exchangeRate")
				.queryParam("fromRate", "fromRate")
				.queryParam("toRate", "toRate")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().is4xxClientError());
	}
}
