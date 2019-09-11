package com.sudhan.goweather;

import com.sudhan.goweather.network.ApiEndpoints;
import com.sudhan.goweather.network.NetworkConstants;
import com.sudhan.goweather.network.NetworkManager;
import com.sudhan.goweather.showforecast.model.CurrentWeatherResponse;
import com.sudhan.goweather.showforecast.model.ForecastWeatherResponse;
import com.sudhan.goweather.showforecast.presenter.IForecastPresenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Single;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ApiTest {

    @Mock
    private ApiEndpoints apiEndpoints;
    @Mock
    private IForecastPresenter iForecastPresnter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCurrentWeatherApi() {
        CurrentWeatherResponse response = new CurrentWeatherResponse();
        when(apiEndpoints.getCurrentWeather(NetworkConstants.INSTANCE.getAPI_KEY(), "Chennai"))
                .thenReturn(Single.just(response));
        new NetworkManager(iForecastPresnter).getCurrentWeatherReport("Chennai");
        InOrder inOrder = Mockito.inOrder(iForecastPresnter);
        inOrder.verify(iForecastPresnter, times(1)).onCurrentWeatherSuccess(response);

        String city = response.location.name;
        Assert.assertEquals(city, "Chennai");
    }

    @Test
    public void testForecastApi() {
        ForecastWeatherResponse response = new ForecastWeatherResponse();
        when(apiEndpoints.getForecastWeather(NetworkConstants.INSTANCE.getAPI_KEY(), "Chennai", 5))
                .thenReturn(Single.just(response));
        new NetworkManager(iForecastPresnter).getForecastWeatherReport("Chennai");
        InOrder inOrder = Mockito.inOrder(iForecastPresnter);
        inOrder.verify(iForecastPresnter, times(1)).onForecastWeatherSuccess(response);

        String city = response.location.name;
        Assert.assertEquals(city, "Chennai");
    }
}
