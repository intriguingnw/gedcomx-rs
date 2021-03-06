/**
 * Copyright 2011-2012 Intellectual Reserve, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gedcomx.rs;

import org.gedcomx.atom.Feed;
import org.gedcomx.rt.rs.*;

import javax.ws.rs.GET;
import javax.ws.rs.core.Response;

/**
 * <p>The discovery resource is the starting point for a Web service API. The discovery resource is the index of all available resources in the application.</p>
 *
 * <p>As a consumer of the API, you should be using _links_ to figure out __at runtime__ where all the resources are. You shouldn't be hard-coding endpoint
 * URLs, nor should you ever have to plug in identifiers into a a URL template to access resources. The only thing your client should need is the URL to the
 * discovery resource.</p>
 *
 * <p>The links provided by the discovery resource should include:</p>
 *
 * <ul>
 *   <li>Links to the authentication mechanism that the system supports.</li>
 *   <li>Links to the root-level resources of the system.</li>
 *   <li>Links that describe the policies for using the application.</li>
 *   <li>Links that assert conformance to the various application profiles supported by the system.</li>
 * </ul>
 *
 * <p>The discovery resource uses an <a href="http://www.ietf.org/rfc/rfc4287">Atom Feed</a> to supply all its links.</p>
 *
 * <p>It is recommended that the discovery resource be mounted at the <tt>/.well-known/app-meta</tt> endpoint. For more information on "well-known" URIs, see
 * <a href="http://tools.ietf.org/html/rfc5785">RFC5785</a>.</p>
 *
 * @author Mike Gardiner
 * @author Ryan Heaton
 */
@ResourceDefinition(
    name = "Discovery",
    projectId = RSModel.RS_PROJECT_ID,
    resourceElement = Feed.class,
    namespace = RSModel.RS_V1_NAMESPACE
)
@ResourceLinks({
  @ResourceLink (rel = PersonsRSDefinition.REL, definedBy = DiscoveryRSDefinition.class, description = "The persons resource for this application."),
  @ResourceLink (rel = PersonRSDefinition.REL, definedBy = PersonRSDefinition.class, description = "The person for the currently logged in user." ),
  @ResourceLink (rel = PersonSummaryRSDefinition.REL, definedBy = PersonSummaryRSDefinition.class, description = "The person summary for the currently logged in user." ),
  @ResourceLink (rel = SearchRSDefinition.REL, definedBy = SearchRSDefinition.class, description = "The search resource for this application (linked via URI template).", template = true )
})
public interface DiscoveryRSDefinition extends CommonRSParameters {

  String REL = GEDCOMX_LINK_REL_PREFIX + "discovery";

  /**
   * Read the host metadata.
   *
   * @return The host metadata.
   */
  @GET
  @StatusCodes({
    @ResponseCode(code = 200, condition = "Upon a successful read.")
  })
  Response get();

}
