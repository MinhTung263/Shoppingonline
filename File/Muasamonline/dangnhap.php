<?php
	require_once('connect.php');
	mysqli_set_charset($connect,'utf8');
	/** Array for JSON response*/
	$response = array();
	if($_SERVER['REQUEST_METHOD']=='POST'){
		$username = $_POST['username'];
		$password = $_POST['password'];
		$sql = "SELECT user_id,user_name, email FROM account WHERE user_name = '$username' AND password='$password'";
		if(mysqli_num_rows(@mysqli_query($connect,$sql)) > 0){
			    $result= mysqli_query($connect,$sql);
                $row = mysqli_fetch_array($result);
				$user_id = $row["user_id"];
				$user_name = $row["user_name"];
				$email = $row["email"];
				
				$response["success"] = 1;
				$response["message"] = "Đăng nhập thành công!";
				$response["user_id"] = $user_id;
				$response["user_name"] = $user_name;
				$response["email"] = $email;
		}else{
			$response["success"] = 0;
			$response["message"] = "Tài khoản hoặc mật khẩu không chính xác!";
		}
		/**Return json*/
		echo json_encode($response);
	} 
?>