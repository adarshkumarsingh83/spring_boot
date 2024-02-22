package com.espark.adarsh.api;

import com.espark.adarsh.bean.ResponseBean;
import com.espark.adarsh.bean.TreeNodeBean;
import com.espark.adarsh.exception.ResourceNotFound;
import com.espark.adarsh.exception.ValidationFailed;
import com.espark.adarsh.service.SearchService;
import com.espark.adarsh.service.TreeService;
import com.espark.adarsh.util.MessageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.espark.adarsh.util.ApplicationUtil.API_RESPONSE;
import static com.espark.adarsh.util.ApplicationUtil.GET_TREE;

@RestController
@Api(value = "TreeController", description = "Tree Operations")
public class TreeController {

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private TreeService treeService;

    @Autowired
    private SearchService searchService;

    @ApiOperation(value = "getTree", nickname = "getTree", notes = "Return the Tree of the Supplied Type Node")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful Response Send"),
            @ApiResponse(code = 404, message = "Data not found")
            , @ApiResponse(code = 500, message = "Server Error")})
    @GetMapping(value = "/tree-{type}")
    public ResponseBean<TreeNodeBean, String> getTree(@ApiParam(name = "type", value = "String to the application", required = true)
                                                          @PathVariable("type") String type,
                                                      @ApiParam(name = "depth", value = "Integer to the application", required = false)
                                                      @RequestParam("depth") Optional<Integer> depthParameter)
            throws ValidationFailed, ResourceNotFound {
        TreeNodeBean treeNodeBean = this.treeService.getTree(type, depthParameter);
        return new ResponseBean(treeNodeBean, HttpStatus.OK, messageUtil.get(API_RESPONSE, new Object[]{GET_TREE, type}));
    }


    @ApiOperation(value = "getTree", nickname = "getTree",
            notes = "Return the Tree of the Supplied Type Node from base root node as supplied id ")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful Response Send"),
            @ApiResponse(code = 404, message = "Data not found")
            , @ApiResponse(code = 500, message = "Server Error")})
    @GetMapping(value = "/tree-{type}/{id}")
    public ResponseBean<TreeNodeBean, String> getTree(
            @ApiParam(name = "type", value = "String to the application", required = true)
            @PathVariable("type") String type,
            @ApiParam(name = "id", value = "Integer to the application", required = true)
            @PathVariable("id") Long id,
            @ApiParam(name = "depth", value = "Integer to the application", required = false)
            @RequestParam("depth") Optional<Integer> depthParameter)
            throws ValidationFailed, ResourceNotFound {
        TreeNodeBean treeNodeBean = this.treeService.getTree(type, id, depthParameter);
        return new ResponseBean(treeNodeBean, HttpStatus.OK, messageUtil.get(API_RESPONSE, new Object[]{GET_TREE, type}));
    }


    @ApiOperation(value = "searchInTree", nickname = "searchInTree",
            notes = "Return the Tree of the Supplied search terms ")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful Response Send"),
            @ApiResponse(code = 404, message = "Data not found")
            , @ApiResponse(code = 500, message = "Server Error")})
    @GetMapping(value = "/tree/{type}/{searchTerm}")
    public ResponseBean<TreeNodeBean, String> searchInTree(
             @ApiParam(name = "type", value = "String to the application", required = true)
             @PathVariable("type") String type
            , @ApiParam(name = "searchTerm", value = "String to the application", required = true)
             @PathVariable("searchTerm") String searchTerm)
            throws ValidationFailed, ResourceNotFound {
        TreeNodeBean treeNodeBean = this.searchService.getTreeSearchResult(searchTerm, type);
        return new ResponseBean(treeNodeBean, HttpStatus.OK, messageUtil.get(API_RESPONSE, new Object[]{GET_TREE, type}));
    }


}
