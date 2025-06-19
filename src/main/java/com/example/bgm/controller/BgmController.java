package com.example.bgm.controller;

import com.example.bgm.dto.ApiResponseDto;
import com.example.bgm.dto.BgmPostResponseDto;
import com.example.bgm.dto.BgmRequestDto;
import com.example.bgm.service.BgmService;
import com.google.firebase.auth.FirebaseToken;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
public class BgmController {

  private final BgmService bgmService;

  @PostMapping("/post_bgm")
  public ResponseEntity<ApiResponseDto<BgmPostResponseDto>> postBgm(
      @AuthenticationPrincipal FirebaseToken decodedToken,
      @Valid @RequestBody BgmRequestDto requestBody) {

    var savedBgm = bgmService.createBgm(requestBody, decodedToken.getUid());

    var responseData = BgmPostResponseDto.fromEntity(savedBgm);

    var responseBody = ApiResponseDto.success(responseData);

    var location =
        ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/get_bgm/{id}")
            .buildAndExpand(savedBgm.getId())
            .toUri();

    return ResponseEntity.created(location).body(responseBody);
  }
}
