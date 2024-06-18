package br.com.clinicmanager.plans.controller;

import br.com.clinicmanager.plans.dto.request.PlanRequestDTO;
import br.com.clinicmanager.plans.dto.response.PlanResponseDTO;
import br.com.clinicmanager.plans.service.PlanService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

@Path("/api/plans")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlanController {

    @Inject
    PlanService planService;

    @POST
    public Response createPlan(PlanRequestDTO planRequestDTO) {
        PlanResponseDTO plan = planService.createPlan(planRequestDTO);
        return Response.status(Response.Status.CREATED).entity(plan).build();
    }

    @GET
    @Path("/{id}")
    public Response getPlanById(@PathParam("id") UUID planId) {
        PlanResponseDTO plan = planService.getPlanById(planId);
        return Response.ok(plan).build();
    }

    @GET
    public List<PlanResponseDTO> getAllPlans() {
        return planService.getAllPlans();
    }

    @PUT
    @Path("/{id}")
    public Response updatePlan(@PathParam("id") UUID planId, PlanRequestDTO planRequestDTO) {
        PlanResponseDTO plan = planService.updatePlan(planId, planRequestDTO);
        return Response.ok(plan).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePlan(@PathParam("id") UUID planId) {
        planService.deletePlan(planId);
        return Response.noContent().build();
    }
}
