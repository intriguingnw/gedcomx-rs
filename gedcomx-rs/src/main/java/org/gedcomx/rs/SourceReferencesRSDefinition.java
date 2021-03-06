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

import org.gedcomx.common.ResourceSet;
import org.gedcomx.conclusion.ConclusionModel;
import org.gedcomx.conclusion.SourceReference;
import org.gedcomx.rt.rs.ResourceDefinition;
import org.gedcomx.rt.rs.ResponseCode;
import org.gedcomx.rt.rs.StatusCodes;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;


/**
 * The source references resource service is used to manage a collection of source references.
 */
@ResourceDefinition(
    name = "Source References",
    namespace = ConclusionModel.GEDCOMX_CONCLUSION_V1_NAMESPACE,
    projectId = RSModel.RS_PROJECT_ID,
    resourceElement = ResourceSet.class,
    subresources = { SourceReferenceRSDefinition.class }
)
public interface SourceReferencesRSDefinition extends CommonRSParameters {

  public static final String REL = GEDCOMX_LINK_REL_PREFIX + "source/references";

  /**
   * Read the references to sources.
   *
   * @return The list of source references.
   */
  @GET
  @StatusCodes({
    @ResponseCode ( code = 200, condition = "Upon a successful read."),
    @ResponseCode ( code = 404, condition = "If the requested resource is not found.")
  })
  Response get();

  /**
   * Create a source reference.
   *
   * @param sourceReference The source reference to be created.
   * @return The appropriate response.
   */
  @POST
  @StatusCodes({
      @ResponseCode( code = 201, condition = "The creation of the source reference was successful. Expect a location header specifying the link to the created source reference."),
      @ResponseCode( code = 400, condition = "If the request was unable to be understood by the application.")
  })
  Response post(SourceReference sourceReference);

}
