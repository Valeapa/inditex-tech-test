package com.valea.inditextechtest.infrastructure.price.controller;

import com.valea.inditextechtest.infrastructure.price.controller.dto.ExceptionDto;
import com.valea.inditextechtest.domain.price.model.Price;
import com.valea.inditextechtest.domain.price.exception.PriceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.OffsetDateTime;

@Validated
@Tag(name = "Price API", description = "The price API allows you to query products applicable prices")
@RequestMapping("/api")
public interface PriceController {

    /**
     * GET /api/price : Get the product's applicable price.
     *
     * @param productId searched product (required)
     * @param brandId group chain (1 = ZARA) (required)
     * @param applicableDateTime price applicable date-time (required)
     * @return Product's applicable price found (status code 200)
     *         or Price Not found (status code 404) / Wrong syntax - missing required attribute (status code 400)
     */
    @Operation(
            operationId = "getApplicablePrice",
            summary = "Get the product's applicable price",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Product's applicable price found", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Price.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Wrong syntax", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDto.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Price not found", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDto.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/price",
            produces = { "application/json" }
    )
    Price getApplicablePrice(@NotNull @Parameter(name = "productId", description = "searched product", required = true, in = ParameterIn.QUERY, example = "35455") @Valid @Positive @RequestParam(value = "productId", required = true) Integer productId,
                             @NotNull @Parameter(name = "brandId", description = "group chain (1 = ZARA)", required = true, in = ParameterIn.QUERY, example = "1") @Valid @Positive @RequestParam(value = "brandId", required = true) Integer brandId,
                             @NotNull @Parameter(name = "applicableDateTime", description = "price applicable date-time", required = true, in = ParameterIn.QUERY, example = "2020-06-14T15:00:00Z") @Valid @RequestParam(value = "applicableDateTime", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime applicableDateTime
                            ) throws PriceNotFoundException;
}
