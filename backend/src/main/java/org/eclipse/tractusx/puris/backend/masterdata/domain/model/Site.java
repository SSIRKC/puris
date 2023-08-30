/*
 * Copyright (c) 2023 Volkswagen AG
 * Copyright (c) 2023 Fraunhofer-Gesellschaft zur Foerderung der angewandten Forschung e.V.
 * (represented by Fraunhofer ISST)
 * Copyright (c) 2023 Contributors to the Eclipse Foundation
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
package org.eclipse.tractusx.puris.backend.masterdata.domain.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * A Site represents a real estate business asset of a business partner.
 * It may be a production plant, office building, warehouse, etc.
 * Each Site is uniquely identified by it's BPNS.
 * For every Site there is at least one business address, which is in turn
 * represented by the {@link Address}.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Site {

    @Id
    @NotNull
    private String bpns;
    private String name;
    @ElementCollection
    private Set<Address> addresses = new HashSet<>();


    /**
     * This constructor generates a new Site.
     * @param bpns the BPNS of this Site
     * @param siteName the human-readable description of this Site
     * @param bpna the BPNA assigned to this Site
     * @param streetAndNumber street and number assigned to the BPNA
     * @param zipCodeAndCity zip code and city assigned to the BPNA
     * @param country the country assigned to the BPNA
     */
    public Site(String bpns, String siteName, String bpna, String streetAndNumber, String zipCodeAndCity, String country) {
        this.bpns = bpns;
        this.name = siteName;
        addresses.add(new Address(bpna, streetAndNumber, zipCodeAndCity, country));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Site) {
            return bpns.equals(((Site) obj).bpns);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return bpns.hashCode();
    }
}
