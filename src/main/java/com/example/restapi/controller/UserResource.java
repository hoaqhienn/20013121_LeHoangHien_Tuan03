package com.example.restapi.controller;

import com.example.restapi.model.User;
import jakarta.inject.Named;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Named
@Path("/users")
public class UserResource {

    @GET
    @Path("/view")
    @Produces("text/json")
    public String viewUser() {
        return "LÊ HOÀNG HIỆN";
    }

    @GET
    @Path("/add/{a}/{b}")
    public int Add(@PathParam("a") int a, @PathParam("b") int b)
    {
        return a+b;
    }

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1, "Lê Hoàng Hiện", "hh@gmail.com"));
        userList.add(new User(2, "Hoàng Hiện", "hien@gmail.com"));
        System.out.println("Lấy danh sách toàn bộ user");
        return Response.ok(userList).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") int id) {
        System.out.println("Tìm kiếm user có ID: " + id);
        if (id == 1) {
            User user = new User(1, "Lê Hoàng Hiện", "hh@gmail.com");
            return Response.ok(user).build();
        }
        else if(id==2){
            User user = new User(2, "Hoàng Hiện", "hien@gmail.com");
            return Response.ok(user).build();
        }
        else
        {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Không tìm thấy người dùng với ID: " + id)
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {

        System.out.println("Tạo user mới: " + user.getName());
        return Response.status(Response.Status.CREATED)
                .entity("Đã thêm người dùng thành công!")
                .build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") int id, User updatedUser) {

        System.out.println("Cập nhật user ID " + id + " thành " +
                updatedUser.getName());
        return Response.ok("Đã cập nhật xong!").build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") int id) {
        System.out.println("Đã xóa user ID: " + id);
        return Response.ok("Xóa người dùng thành công!").build();
    }
}
