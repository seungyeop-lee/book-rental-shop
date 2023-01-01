package service

import (
	"github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/application/port/in"
	"github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/application/port/out"
	"github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/vo"
)

type memberService struct {
	finder  out.MemberFinder
	saver   out.MemberSaver
	updater out.MemberUpdater
	deleter out.MemberDeleter
}

var _ in.MemberUseCase = (*memberService)(nil)

func NewMemberService(finder out.MemberFinder, saver out.MemberSaver, updater out.MemberUpdater, deleter out.MemberDeleter) *memberService {
	return &memberService{
		finder:  finder,
		saver:   saver,
		updater: updater,
		deleter: deleter,
	}
}

func (m memberService) RegisterMember(param in.MemberCreateCommand) (vo.MemberId, error) {
	member := param.MapToMember()
	return m.saver.Save(member)
}

func (m memberService) ReadMember(id vo.MemberId) (*in.MemberReadResult, error) {
	found, err := m.finder.FindById(id)
	if err != nil {
		return nil, err
	}

	return in.NewMemberReadResultByMember(found), nil
}

func (m memberService) UpdateMember(id vo.MemberId, param in.MemberUpdateCommand) error {
	member := param.MapToMember()
	return m.updater.Update(id, member)
}

func (m memberService) DeleteMember(id vo.MemberId) error {
	return m.deleter.Delete(id)
}
