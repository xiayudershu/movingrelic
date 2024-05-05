package org.xiayudeshu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xiayudeshu.Result;
import org.xiayudeshu.pojo.dto.*;
import org.xiayudeshu.service.*;

@RestController
@RequestMapping("/backend")
public class FrontendController {
    @Autowired
    private LoginAndSetupService loginAndSetupService;

    @Autowired
    private PostService postService;

    @Autowired
    private LostService lostService;

    @Autowired
    private CreationService creationService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result UserLogIn(@RequestBody UserLogin userLogin){
        Long code= loginAndSetupService.UserLogin(userLogin);
         if(code==-1L){
             return Result.error("用户名不存在！请注册！");
         }
        else if(code==0L){
            return Result.error("用户密码错误！");
        }
        else{
            return Result.success(code);
         }

    }

    @PostMapping("/setup")
    public Result UserSetup(@RequestBody UserSetup userSetup){
        Long code= loginAndSetupService.UserSetup(userSetup);
        if(code==-1L){
            return Result.error("用户名已存在！请换一个昵称！");
        }
        else{
            return Result.success(code);
        }
    }


    @PostMapping("/editinform")
    public Result UserEditInform(@RequestBody EditUserInform editUserInform){
        return Result.success(userService.EditUserInform(editUserInform));
    }

    @GetMapping("/getallstars/{userId}")
    public Result GetALLStars(@PathVariable Long userId){
        return Result.success(userService.GetALLStars(userId));
    }

    @GetMapping("/getuserinform/{userId}")
    public Result GetInform(@PathVariable Long userId){
        return Result.success(userService.GetInform(userId));
    }



    @PostMapping("/addpost")
    public Result AddPost(@RequestBody AddPost addPost){
        Long code= postService.AddPost(addPost); ;
        if(code==-1L){
            return Result.error("发布失败！请重新发布");
        }
        else{
            return Result.success(code);

       }
    }
    @GetMapping("/getposts")
    public Result GetPosts(){
      return Result.success(postService.GetPosts());
    }

    @GetMapping("/getpostdetail/{postId}")
    public Result GetPostDetail(@PathVariable Long postId){
        return Result.success(postService.getPostDetail(postId));
    }

    @PostMapping("/addpostcomment")
    public Result AddPostComment(@RequestBody AddPostComment addPostComment){
        return Result.success(postService.AddPostComment(addPostComment));
    }

    @PostMapping("/likepost")
    public Result LikePost(@RequestBody LikePost likePost){
        postService.LikePost(likePost);
        return Result.success();
    }

    @PostMapping("/dislikepost")
    public Result DislikePost(@RequestBody LikePost likePost){
        postService.DislikePost(likePost);
        return Result.success();
    }

    @PostMapping("/gettargetpost/usual")
    public Result GetTargetPostUsual(@RequestBody SearchPost searchPost){
        return Result.success(postService.GetTargetPosts(searchPost,"帖子"));
    }



    @PostMapping("/gettargetpost/identify")
    public Result GetTargetPostIdentify(@RequestBody SearchPost searchPost){
        return Result.success(postService.GetTargetPosts(searchPost,"鉴宝"));
    }





    @PostMapping("/addcreation")
    public Result AddCreation(@RequestBody AddCreation addCreation){
        Long code= creationService.AddCreation(addCreation); ;
        if(code==-1L){
            return Result.error("发布失败！请重新发布");
        }
        else{
            return Result.success(code);

        }
    }
    @GetMapping("/getcreations")
    public Result GetCreations(){
        return Result.success(creationService.GetCreations());
    }

    @PostMapping("/gettargetcreation")
    public Result GetTargetCreation(@RequestBody SearchCreation searchCreation){
        return Result.success(creationService.GetTargetCreations(searchCreation));
    }


    @GetMapping("/getcreationdetail/{creationId}")
    public Result GetCreationDetail(@PathVariable Long creationId){
        return Result.success(creationService.getCreationDetail(creationId));
    }

    @PostMapping("/addcreationcomment")
    public Result AddCreationComment(@RequestBody AddCreationComment addCreationComment){
        return Result.success(creationService.AddCreationComment(addCreationComment));
    }

    @PostMapping("/likecreation")
    public Result LikeCreation(@RequestBody LikeCreation likeCreation){
        creationService.LikeCreation(likeCreation);
        return Result.success();
    }

    @PostMapping("/dislikecreation")
    public Result DislikeCreation(@RequestBody LikeCreation likeCreation){
        creationService.DislikeCreation(likeCreation);
        return Result.success();
    }





    @GetMapping("/getlosts")
    public Result GetLosts(){
        return Result.success(lostService.GetLosts());
    }

    @GetMapping("/getlostdetail/{lostCreationId}")
    public Result GetLostDetail(@PathVariable Long lostCreationId){
        return Result.success(lostService.getLostDetail(lostCreationId));
    }

    @PostMapping("/gettargetlost")
    public Result GetTargetLost(@RequestBody SearchLost searchLost){
        return Result.success(lostService.GetTargetLosts(searchLost));
    }

    @PostMapping("/addlostcomment")
    public Result AddLostComment(@RequestBody AddLostComment addLostComment){
        return Result.success(lostService.AddLostComment(addLostComment));
    }

    @PostMapping("/likelost")
    public Result LikeLost(@RequestBody LikeLost likeLost){
        lostService.LikeLost(likeLost);
        return Result.success();
    }

    @PostMapping("/dislikelost")
    public Result DislikeLost(@RequestBody LikeLost likeLost){
        lostService.DislikeLost(likeLost);
        return Result.success();
    }
}