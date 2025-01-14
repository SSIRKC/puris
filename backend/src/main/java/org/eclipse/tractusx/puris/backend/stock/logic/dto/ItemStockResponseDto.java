/*
 * Copyright (c) 2023, 2024 Volkswagen AG
 * Copyright (c) 2023, 2024 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Apache License, Version 2.0 which is available at
 * https://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package org.eclipse.tractusx.puris.backend.stock.logic.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.eclipse.tractusx.puris.backend.common.util.PatternStore;
import org.eclipse.tractusx.puris.backend.stock.logic.dto.itemstocksamm.ItemStockSamm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
/**
 * This class represents a response message as it is sent
 * to the counterparty.
 */
public class ItemStockResponseDto {

    @NotNull
    private HeaderDto header = new HeaderDto();
    @NotNull
    private ContentDto content = new ContentDto();

    @Getter
    @Setter
    @ToString
    public static class HeaderDto {
        @NotNull
        private UUID messageId;
        @NotNull
        private UUID relatedMessageId;
        @NotNull
        @Pattern(regexp = PatternStore.NON_EMPTY_NON_VERTICAL_WHITESPACE_STRING)
        private String context;
        @NotNull
        @Pattern(regexp = PatternStore.NON_EMPTY_NON_VERTICAL_WHITESPACE_STRING)
        private String version;
        @NotNull
        @Pattern(regexp = PatternStore.BPNL_STRING)
        private String senderBpn;
        @NotNull
        @Pattern(regexp = PatternStore.BPNL_STRING)
        private String receiverBpn;
        @NotNull
        private Date sentDateTime;
    }

    @Getter
    @Setter
    @ToString
    public static class ContentDto {
        List<ItemStockSamm> itemStock = new ArrayList<>();
    }
}
