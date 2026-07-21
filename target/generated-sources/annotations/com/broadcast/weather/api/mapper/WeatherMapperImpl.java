package com.broadcast.weather.api.mapper;

import com.broadcast.weather.api.client.dto.WeatherApiCurrentResponse;
import com.broadcast.weather.api.client.dto.WeatherApiForecastResponse;
import com.broadcast.weather.api.client.dto.WeatherApiSearchResponse;
import com.broadcast.weather.api.model.CurrentWeather;
import com.broadcast.weather.api.model.DailyForecast;
import com.broadcast.weather.api.model.ForecastData;
import com.broadcast.weather.api.model.ForecastResponse;
import com.broadcast.weather.api.model.Location;
import com.broadcast.weather.api.model.WeatherResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-21T10:07:58+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class WeatherMapperImpl implements WeatherMapper {

    @Override
    public WeatherResponse mapToWeatherResponse(WeatherApiCurrentResponse response) {
        if ( response == null ) {
            return null;
        }

        WeatherResponse weatherResponse = new WeatherResponse();

        if ( response.location() != null ) {
            if ( weatherResponse.getData() == null ) {
                weatherResponse.setData( new CurrentWeather() );
            }
            locationDtoToCurrentWeather( response.location(), weatherResponse.getData() );
        }
        if ( response.current() != null ) {
            if ( weatherResponse.getData() == null ) {
                weatherResponse.setData( new CurrentWeather() );
            }
            currentDtoToCurrentWeather( response.current(), weatherResponse.getData() );
        }

        return weatherResponse;
    }

    @Override
    public ForecastResponse mapToForecastResponse(WeatherApiForecastResponse response) {
        if ( response == null ) {
            return null;
        }

        ForecastResponse forecastResponse = new ForecastResponse();

        if ( response.location() != null ) {
            if ( forecastResponse.getData() == null ) {
                forecastResponse.setData( new ForecastData() );
            }
            locationDtoToForecastData( response.location(), forecastResponse.getData() );
        }
        if ( response.forecast() != null ) {
            if ( forecastResponse.getData() == null ) {
                forecastResponse.setData( new ForecastData() );
            }
            forecastDtoToForecastData( response.forecast(), forecastResponse.getData() );
        }

        return forecastResponse;
    }

    @Override
    public DailyForecast map(WeatherApiForecastResponse.ForecastDayDto forecastDayDto) {
        if ( forecastDayDto == null ) {
            return null;
        }

        DailyForecast dailyForecast = new DailyForecast();

        dailyForecast.setMaxTempCelsius( forecastDayDtoDayMaxTempC( forecastDayDto ) );
        dailyForecast.setMinTempCelsius( forecastDayDtoDayMinTempC( forecastDayDto ) );
        dailyForecast.setCondition( forecastDayDtoDayConditionText( forecastDayDto ) );
        dailyForecast.setConditionIcon( forecastDayDtoDayConditionIcon( forecastDayDto ) );
        dailyForecast.setChanceOfRain( forecastDayDtoDayDailyChanceOfRain( forecastDayDto ) );
        dailyForecast.setMaxWindSpeedKph( forecastDayDtoDayMaxWindKph( forecastDayDto ) );

        dailyForecast.setDate( java.time.LocalDate.parse(forecastDayDto.date()) );

        return dailyForecast;
    }

    @Override
    public List<Location> mapToLocationList(List<WeatherApiSearchResponse> responses) {
        if ( responses == null ) {
            return null;
        }

        List<Location> list = new ArrayList<Location>( responses.size() );
        for ( WeatherApiSearchResponse weatherApiSearchResponse : responses ) {
            list.add( map( weatherApiSearchResponse ) );
        }

        return list;
    }

    @Override
    public Location map(WeatherApiSearchResponse response) {
        if ( response == null ) {
            return null;
        }

        Location location = new Location();

        location.setLatitude( response.lat() );
        location.setLongitude( response.lon() );
        location.setName( response.name() );
        location.setRegion( response.region() );
        location.setCountry( response.country() );

        return location;
    }

    protected void locationDtoToCurrentWeather(WeatherApiCurrentResponse.LocationDto locationDto, CurrentWeather mappingTarget) {
        if ( locationDto == null ) {
            return;
        }

        mappingTarget.setLocation( locationDto.name() );
        mappingTarget.setCountry( locationDto.country() );
    }

    private String currentDtoConditionText(WeatherApiCurrentResponse.CurrentDto currentDto) {
        WeatherApiCurrentResponse.ConditionDto condition = currentDto.condition();
        if ( condition == null ) {
            return null;
        }
        return condition.text();
    }

    private String currentDtoConditionIcon(WeatherApiCurrentResponse.CurrentDto currentDto) {
        WeatherApiCurrentResponse.ConditionDto condition = currentDto.condition();
        if ( condition == null ) {
            return null;
        }
        return condition.icon();
    }

    protected void currentDtoToCurrentWeather(WeatherApiCurrentResponse.CurrentDto currentDto, CurrentWeather mappingTarget) {
        if ( currentDto == null ) {
            return;
        }

        mappingTarget.setTemperatureCelsius( currentDto.tempC() );
        mappingTarget.setTemperatureFahrenheit( currentDto.tempF() );
        mappingTarget.setCondition( currentDtoConditionText( currentDto ) );
        mappingTarget.setConditionIcon( currentDtoConditionIcon( currentDto ) );
        mappingTarget.setWindSpeedKph( currentDto.windKph() );
        mappingTarget.setHumidity( currentDto.humidity() );
        mappingTarget.setLastUpdated( parseLastUpdated( currentDto.lastUpdated() ) );
    }

    protected void locationDtoToForecastData(WeatherApiCurrentResponse.LocationDto locationDto, ForecastData mappingTarget) {
        if ( locationDto == null ) {
            return;
        }

        mappingTarget.setLocation( locationDto.name() );
        mappingTarget.setCountry( locationDto.country() );
    }

    protected List<DailyForecast> forecastDayDtoListToDailyForecastList(List<WeatherApiForecastResponse.ForecastDayDto> list) {
        if ( list == null ) {
            return null;
        }

        List<DailyForecast> list1 = new ArrayList<DailyForecast>( list.size() );
        for ( WeatherApiForecastResponse.ForecastDayDto forecastDayDto : list ) {
            list1.add( map( forecastDayDto ) );
        }

        return list1;
    }

    protected void forecastDtoToForecastData(WeatherApiForecastResponse.ForecastDto forecastDto, ForecastData mappingTarget) {
        if ( forecastDto == null ) {
            return;
        }

        if ( mappingTarget.getDays() != null ) {
            List<DailyForecast> list = forecastDayDtoListToDailyForecastList( forecastDto.forecastDay() );
            if ( list != null ) {
                mappingTarget.getDays().clear();
                mappingTarget.getDays().addAll( list );
            }
            else {
                mappingTarget.setDays( null );
            }
        }
        else {
            List<DailyForecast> list = forecastDayDtoListToDailyForecastList( forecastDto.forecastDay() );
            if ( list != null ) {
                mappingTarget.setDays( list );
            }
        }
    }

    private Double forecastDayDtoDayMaxTempC(WeatherApiForecastResponse.ForecastDayDto forecastDayDto) {
        WeatherApiForecastResponse.DayDto day = forecastDayDto.day();
        if ( day == null ) {
            return null;
        }
        return day.maxTempC();
    }

    private Double forecastDayDtoDayMinTempC(WeatherApiForecastResponse.ForecastDayDto forecastDayDto) {
        WeatherApiForecastResponse.DayDto day = forecastDayDto.day();
        if ( day == null ) {
            return null;
        }
        return day.minTempC();
    }

    private String forecastDayDtoDayConditionText(WeatherApiForecastResponse.ForecastDayDto forecastDayDto) {
        WeatherApiForecastResponse.DayDto day = forecastDayDto.day();
        if ( day == null ) {
            return null;
        }
        WeatherApiCurrentResponse.ConditionDto condition = day.condition();
        if ( condition == null ) {
            return null;
        }
        return condition.text();
    }

    private String forecastDayDtoDayConditionIcon(WeatherApiForecastResponse.ForecastDayDto forecastDayDto) {
        WeatherApiForecastResponse.DayDto day = forecastDayDto.day();
        if ( day == null ) {
            return null;
        }
        WeatherApiCurrentResponse.ConditionDto condition = day.condition();
        if ( condition == null ) {
            return null;
        }
        return condition.icon();
    }

    private Integer forecastDayDtoDayDailyChanceOfRain(WeatherApiForecastResponse.ForecastDayDto forecastDayDto) {
        WeatherApiForecastResponse.DayDto day = forecastDayDto.day();
        if ( day == null ) {
            return null;
        }
        return day.dailyChanceOfRain();
    }

    private Double forecastDayDtoDayMaxWindKph(WeatherApiForecastResponse.ForecastDayDto forecastDayDto) {
        WeatherApiForecastResponse.DayDto day = forecastDayDto.day();
        if ( day == null ) {
            return null;
        }
        return day.maxWindKph();
    }
}
