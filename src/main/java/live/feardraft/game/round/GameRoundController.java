package live.feardraft.game.round;

import live.feardraft.game.dto.PickClickRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GameRoundController {

    private final SimpMessagingTemplate template;
    private final GameRoundService gameRoundService;

    @MessageMapping("/clickaround")
    public void pickClick(@Payload final PickClickRequest request) {
        template.convertAndSend("/sub/clickaround/" + request.getGameId(), request);
    }

    @MessageMapping("/pickclick")
    public void pickLock(@Payload final PickClickRequest request) {
        gameRoundService.pickLock(request);
        template.convertAndSend("/sub/pick/" + request.getGameId(), request);
    }

}
