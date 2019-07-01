<?php 
require_once "connect.php";
$sql="SELECT * FROM quangcao  ";
	$data=mysqli_query($connect,$sql);	
class QuangCao{
	function QuangCao($idQuangCao,$TenQuangCao,$HinhQuangCao,$idSP){
		$this->idQuangCao=$idQuangCao;
		$this->TenQuangCao=$TenQuangCao;
		$this->HinhQuangCao=$HinhQuangCao;
		$this->idSP=$idSP;
	}
}
	$MangQuangCao= array();
	while ($row=mysqli_fetch_assoc($data)) {
		array_push($MangQuangCao, new QuangCao($row["idQuangCao"],$row["TenQuangCao"],$row["HinhQuangCao"],$row["idSP"]));
	}
	echo json_encode($MangQuangCao);
 ?>